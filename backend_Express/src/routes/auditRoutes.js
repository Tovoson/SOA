const express = require("express");
const router = express.Router();
const auditController = require("../controllers/auditController");

// Route pour obtenir tous les logs d'audit
router.get("/", auditController.getAuditLogs);

module.exports = router;
