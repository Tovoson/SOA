const db = require("../db");

const ELEMENTS = ["employe", "compagnie_assurance"];
// Obtenir
exports.getAll = (req, res) => {
  const table = req.params.table;
  if (!ELEMENTS.includes(table)) {
    return res.status(400).json({ message: "Table invalide" });
  }
  db.query(`SELECT * FROM ${table}`, (err, rows) => {
    err
      ? res.status(500).json({
          message: "Erreur lors de la récupération des données",
          error: err,
        })
      : res.status(200).json(rows);
  });
};
// ajout
exports.add = (req, res) => {
  const table = req.params.table;
  if (!ELEMENTS.includes(table)) {
    return res.status(400).json({ message: "Table invalide" });
  }
  const params = Object.keys(req.body);
  if (params.length === 0)
    return res.status(400).json({ message: "Données manquantes" });

  const sql = `INSERT INTO ${table} (${params.join(",")}) VALUES (${params
    .map(() => "?")
    .join(",")})`;
  db.query(sql, Object.values(req.body), (err) => {
    err
      ? res
          .status(500)
          .json({ message: "Erreur lors de l'insertion", error: err })
      : res.status(201).json({ message: "Insertion réussie" });
  });
};

// Mettre à jour un employé
exports.update = (req, res) => {
  const table = req.params.table;
  const primaryKey = req.params.id;
  if (!ELEMENTS.includes(table)) {
    return res.status(400).json({ message: "Table invalide" });
  }
  const params = Object.keys(req.body);
  const { matricule } = req.body;

  if (!req.params.id)
    return res.status(400).json({ message: "Id requis pour la mise à jour" });

  const sql = `UPDATE ${table} SET ${params
    .map((param) => `${param} = ?`)
    .join(", ")} WHERE ${primaryKey} = ?`;
  db.query(sql, [...Object.values(req.body), matricule], (err) => {
    err
      ? res
          .status(500)
          .json({ message: "Erreur lors de la mise à jour", error: err })
      : res.status(200).json({ message: "Mise à jour réussie" });
  });
};

// Supprimer un employé
exports.delete = (req, res) => {
  const table = req.params.table;
  const primaryKey = req.params.key; 
  const matricule  = req.params.id;
  const sql = `DELETE FROM ${table} WHERE ${primaryKey} = ?`;
  db.query(sql, matricule, (err) => {
    err
      ? res
          .status(500)
          .json({ message: "Erreur lors de la suppression", error: err })
      : res.status(200).json({ message: "Suppression réussie" });
  });
};
