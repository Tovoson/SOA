from rest_framework import routers, serializers, viewsets
from employee.models import Employee

class EmployeSerializer(serializers.ModelSerializer):
    class Meta:
        model = Employee
        fields = "__all__"