
import inspect
from importlib import import_module
from fastapi import APIRouter
import os

current_dir = os.path.dirname(os.path.abspath(__file__))
package_name = __package__

root_router = APIRouter()

for file in os.listdir(current_dir):
    if file.endswith('.py') and not file.startswith("__"):
        module_name = file[:-3]
        full_module_name = f"{package_name}.{module_name}" if package_name else module_name
        imported_module = import_module(full_module_name)
        for name, obj in inspect.getmembers(imported_module):
            if isinstance(obj, APIRouter):
                root_router.include_router(obj)

__all__ = ["root_router"]
