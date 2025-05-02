const express = require("express");
const router = express.Router();
const controller = require("../controllers/controller");

router.get("/:table", controller.getAll);
router.post("/:table", controller.add);

module.exports = router;