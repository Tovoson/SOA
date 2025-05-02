const express = require("express");
const router = express.Router();
const employeController = require("../controllers/controller");

// Routes CRUD pour les employés
router.get("/:table", employeController.getAll);
router.post("/:table", employeController.add);
router.put("/:table/:id", employeController.update);
router.delete("/:table/:key/:id", employeController.delete);

module.exports = router;
