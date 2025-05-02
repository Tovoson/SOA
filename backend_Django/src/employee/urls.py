from rest_framework import routers
from employee.views import EmployeeViewSet
from django.urls import include, path

router = routers.DefaultRouter()
router.register('employee', EmployeeViewSet, basename='employee')

urlpatterns = [
    path('', include(router.urls))
]