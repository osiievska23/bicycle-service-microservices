insert into service.repair_service (title, price)
values  ('Regular Service', 1100),
        ('Full bike realignment & external lube', 3399),
        ('Full Overhaul and Repair', 2560),
        ('Hand built wheel', 900)
on conflict (title) do nothing;