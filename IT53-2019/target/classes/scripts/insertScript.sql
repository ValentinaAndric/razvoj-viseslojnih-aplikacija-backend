insert into preduzece values(nextval('preduzece_seq'),'Levi9', 12345678,'Novi Sad', 
'In 1994, the comet Shoemak...');							 
insert into preduzece values(nextval('preduzece_seq'), 'HTEC', 23456789,'Novi Sad', 'Nice');
insert into preduzece values(nextval('preduzece_seq'),'Vega', 23478864,'Novi Sad',  'Great');
insert into preduzece values(nextval('preduzece_seq'), 'Vivify', 65740947,'Novi Sad', 'nice');
insert into preduzece values (nextval('preduzece_seq'), 'Microsoft', 64730222, 'Beograd','nice');
insert into preduzece values (nextval('preduzece_seq'), 'MS', 64730222, 'Beograd','nice');

insert into obrazovanje values (nextval('obrazovanje_seq'), 'Faculty of technical sciences', 'MAS', 'Graduation date 2010');
insert into obrazovanje values (nextval('obrazovanje_seq'), 'Faculty of economics', 'MAS', 'Graduation date 2000');
insert into obrazovanje values (nextval('obrazovanje_seq'), 'Faculty of philosophy', 'OAS', 'Graduation date 2015');
insert into obrazovanje values (nextval('obrazovanje_seq'), 'Faculty of technical sciences', 'OAS', 'Graduation date 2009');
insert into obrazovanje values (nextval('obrazovanje_seq'), 'Faculty of technical sciences', 'OAS', 'Graduation date 2009');

								
insert into sektor values (nextval('sektor_seq'), 'Marketing', 'MT',1);
insert into sektor values (nextval('sektor_seq'), 'Finansije', 'FF',2);
insert into sektor values (nextval('sektor_seq'), 'Proizvodnja', 'P',2);
insert into sektor values (nextval('sektor_seq'), 'Administracija', 'MT',3);
insert into sektor values (nextval('sektor_seq'), 'Pravna sluzba', 'MT',2);

								
insert into radnik values (nextval('radnik_seq'), 'Marko', 'Markovic', 25645, 1,1);
insert into radnik values (nextval('radnik_seq'), 'Ivana', 'Ivanovic', 87654, 2,2);
insert into radnik values (nextval('radnik_seq'), 'Darko', 'Petrovic', 25645, 3,3);
insert into radnik values (nextval('radnik_seq'), 'Mirko', 'Mirkovic', 25645, 4,4);
insert into radnik values (nextval('radnik_seq'), 'Valentina', 'Andric', 25645, 5,5);
insert into radnik values (nextval('radnik_seq'), 'Marko', 'Jovanovic', 25645, 2,3);
								
