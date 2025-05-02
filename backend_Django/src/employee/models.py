from django.db import models

# Create your models here.
class Employee(models.Model):
    numEmploye = models.CharField(max_length = 200, primary_key = True)
    nom = models.CharField(max_length = 200)
    adresse = models.CharField(max_length = 200)
    email = models.EmailField(max_length = 200)