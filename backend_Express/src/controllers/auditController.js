const db = require("../db");

// Obtenir tous les logs d'audit
exports.getAuditLogs = (req, res) => {
  db.query("SELECT * FROM audit_employee ORDER BY update_date DESC", (err, result) => {
    if (err) {
      return res.status(500).json({ error: err.message });
    }
    res.json(result);
  });
};
