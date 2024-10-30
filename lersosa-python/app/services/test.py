from fastapi import HTTPException

fake_items_db = {"plumbus": {"name": "Plumbus"}, "gun": {"name": "Portal Gun"}}

async def read_items():
    return {"你好！"}

async def read_item(item_id: str):
    return {"name": fake_items_db[item_id]["name"], "item_id": item_id}

async def update_item(item_id: str):
    return {"item_id": item_id, "name": "The great Plumbus"}
