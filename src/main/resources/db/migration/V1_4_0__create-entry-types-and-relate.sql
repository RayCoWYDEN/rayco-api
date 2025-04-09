insert into rayco_schema.entry_types("name")
values ('Enem'), ('Vestibular');

insert into rayco_schema.university_entry_type
	(select u.id, et.id from rayco_schema.universities u 
	left join rayco_schema.entry_types et on 1=1);
