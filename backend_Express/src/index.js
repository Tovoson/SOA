require("dotenv").config();
const express = require("express");
const db = require("./db");

const app = express();
app.use(express.json());
app.use(require("cors")());

// Import des routes
const employeRoutes = require("./routes/employeRoutes");
const auditRoutes = require("./routes/auditRoutes");
const userRoutes = require("./routes/userRoutes");

app.use("/api/audit", auditRoutes);
app.use("/api/users", userRoutes);
app.use("/api/employes", employeRoutes);

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => console.log(` Serveur démarré sur le port ${PORT}`));
