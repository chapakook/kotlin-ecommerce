DROP TABLE IF EXISTS `products`;

create table `products`
(
    `price`      bigint       not null,
    `product_id` bigint auto_increment primary key,
    `name`       varchar(255) not null
);

INSERT INTO `products` (`product_id`,`name`,`price`)
VALUES
  (1,"Zeph Boyer",3471),
  (2,"Zenia Garza",1693),
  (3,"Igor Bond",3685),
  (4,"Kennan Nash",4693),
  (5,"Minerva Woodard",7298),
  (6,"Iona Knapp",9024),
  (7,"Matthew Mullins",7010),
  (8,"Erasmus Hampton",6290),
  (9,"Nissim Santos",9446),
  (10,"Brenden Lott",8943),
  (11,"Rebekah Goff",3994),
  (12,"Wayne Cantu",3742),
  (13,"Brian Gentry",7827),
  (14,"Charlotte Wall",6499),
  (15,"Charity Hansen",7198),
  (16,"Amber Jacobson",9856),
  (17,"Dieter Skinner",5861),
  (18,"Genevieve Burt",9462),
  (19,"Mark Nixon",4886),
  (20,"Gannon Gutierrez",7948),
  (21,"Harper Rocha",7312),
  (22,"Hiroko Skinner",1885),
  (23,"Lee Baird",9600),
  (24,"Rana Barton",9935),
  (25,"Nissim Horn",4706),
  (26,"Channing Rice",6467),
  (27,"Medge Mcneil",1362),
  (28,"Susan Cash",4283),
  (29,"Steven Simmons",6843),
  (30,"Shellie Bonner",9643),
  (31,"Madeson Jimenez",5591),
  (32,"Ralph Schwartz",4963),
  (33,"Urielle Garrett",6593),
  (34,"Ira Robbins",692),
  (35,"Quyn Lambert",1271),
  (36,"Candace Nicholson",9503),
  (37,"Sigourney Langley",9990),
  (38,"Lars Oneal",4619),
  (39,"Frances Gibson",8572),
  (40,"Matthew Sykes",2008),
  (41,"Fredericka Riley",3850),
  (42,"Kadeem Henson",9883),
  (43,"Marshall Sellers",1403),
  (44,"Tyrone Kinney",9245),
  (45,"Pearl Burt",8867),
  (46,"Sara Clark",9285),
  (47,"Jason Bond",4703),
  (48,"Jena Crane",3495),
  (49,"Connor Blankenship",7408),
  (50,"Melanie Gilmore",6274);
INSERT INTO `products` (`product_id`,`name`,`price`)
VALUES
  (51,"Vivien Coffey",8690),
  (52,"Nero Jordan",5122),
  (53,"Karly Fields",5548),
  (54,"Lyle Jennings",3265),
  (55,"Hamish Aguilar",9494),
  (56,"Griffin Parks",8486),
  (57,"Cooper Francis",4168),
  (58,"Ciaran Waters",1632),
  (59,"Mechelle Pugh",1549),
  (60,"Anjolie Underwood",8806),
  (61,"Helen Alvarado",5307),
  (62,"Zephr Francis",7371),
  (63,"Hedley Ryan",5692),
  (64,"Anthony Hunt",8543),
  (65,"Amethyst Carr",2558),
  (66,"Kathleen Riggs",6285),
  (67,"Brenda Howell",9950),
  (68,"Janna Flynn",900),
  (69,"Bruno Middleton",9924),
  (70,"Renee Crosby",7963),
  (71,"Kermit Serrano",6011),
  (72,"Petra Yates",5632),
  (73,"Chloe Copeland",8330),
  (74,"Oleg Rice",1006),
  (75,"Maile Logan",521),
  (76,"Finn Richard",1935),
  (77,"Christian Farley",3019),
  (78,"Erich Hays",1435),
  (79,"Hamish Rivers",7951),
  (80,"Wayne Tran",4155),
  (81,"Reed Nieves",4988),
  (82,"Tasha Chaney",5849),
  (83,"Dominique Rodriquez",1634),
  (84,"Jane White",2851),
  (85,"Audra Baxter",6381),
  (86,"Stacy Marshall",3492),
  (87,"Aurelia Parker",8330),
  (88,"Len Avery",6026),
  (89,"Zachary Henson",5379),
  (90,"Mason Pena",875),
  (91,"Kirk Kirkland",5491),
  (92,"Hall Camacho",9942),
  (93,"Kaseem Ryan",2353),
  (94,"Ariel Beasley",2691),
  (95,"Genevieve Ayala",7388),
  (96,"Shaeleigh Maxwell",7630),
  (97,"Ferdinand Drake",3298),
  (98,"Conan Espinoza",9257),
  (99,"Adena Bailey",9322),
  (100,"Hadassah Strickland",1164);
INSERT INTO `products` (`product_id`,`name`,`price`)
VALUES
  (101,"Upton Houston",8817),
  (102,"Moana Chen",9123),
  (103,"Vivien Fisher",513),
  (104,"Mercedes Delaney",8951),
  (105,"Yardley Crawford",4136),
  (106,"Shad Oneal",2929),
  (107,"Anjolie Hughes",3314),
  (108,"Dana Delacruz",8171),
  (109,"Alexa Ortega",4396),
  (110,"Sebastian Mclaughlin",893),
  (111,"Dane Payne",593),
  (112,"Cain Lindsay",8619),
  (113,"Justin Wilkins",8578),
  (114,"Tanner Hebert",5077),
  (115,"Connor Rocha",5546),
  (116,"Bert Pugh",7347),
  (117,"Lara Beach",3456),
  (118,"Jonas Crosby",8577),
  (119,"Kenneth Williamson",2055),
  (120,"Isabelle Meadows",6828),
  (121,"Regina Richard",4427),
  (122,"Bree Alston",9977),
  (123,"Anne Schroeder",1519),
  (124,"Vladimir Deleon",3970),
  (125,"Justina Montoya",8109),
  (126,"Hunter Cole",9523),
  (127,"Holly Willis",4716),
  (128,"Tallulah Black",1483),
  (129,"Dolan Mcmillan",7459),
  (130,"Travis Koch",4029),
  (131,"Colt Pugh",7302),
  (132,"Olivia Bender",6852),
  (133,"Keane Harvey",4846),
  (134,"Tashya Compton",9740),
  (135,"Alden Manning",4701),
  (136,"Giselle Sosa",7401),
  (137,"Micah Bowen",3493),
  (138,"Tanek Charles",6898),
  (139,"Suki Foster",3933),
  (140,"Timothy Horn",1985),
  (141,"Briar Galloway",7569),
  (142,"Suki Briggs",5467),
  (143,"Serena Tillman",6192),
  (144,"Ciaran Conley",8140),
  (145,"Melanie Stein",1514),
  (146,"Bree Mckee",1249),
  (147,"Cruz Flowers",2022),
  (148,"Zephr Dunlap",7392),
  (149,"Abigail Phelps",9076),
  (150,"Carl Hurley",7819);
INSERT INTO `products` (`product_id`,`name`,`price`)
VALUES
  (151,"Daria Beach",2232),
  (152,"Kieran Fuentes",2283),
  (153,"Libby Mccullough",9504),
  (154,"Nichole Lowery",7101),
  (155,"Quail Blanchard",5860),
  (156,"Brennan Mendez",9423),
  (157,"Rafael Irwin",7181),
  (158,"Hop Beasley",3771),
  (159,"Dominic Lowery",4040),
  (160,"Whoopi Cervantes",9865),
  (161,"Myles Finley",6042),
  (162,"Amir Holt",3630),
  (163,"Yoshio Compton",6814),
  (164,"Halla Warner",6627),
  (165,"Anika Hensley",572),
  (166,"David Young",7229),
  (167,"Deanna Fernandez",8340),
  (168,"Bevis Cummings",1030),
  (169,"Rogan Walker",6473),
  (170,"Hayden Blevins",4844),
  (171,"Candace Johns",8638),
  (172,"Ulric Figueroa",5870),
  (173,"Fallon Johnson",7929),
  (174,"Caleb Glass",2346),
  (175,"Gay Fields",8218),
  (176,"Dai Best",1115),
  (177,"Jamalia Bowman",6226),
  (178,"Malik Lester",9758),
  (179,"Ferdinand Robles",9085),
  (180,"Donna Singleton",9488),
  (181,"Boris Howard",8344),
  (182,"Ignacia Perkins",8819),
  (183,"Camden Larsen",521),
  (184,"Zachery Bryant",3113),
  (185,"Unity Kelly",2250),
  (186,"Dexter Mcgee",7954),
  (187,"Jeremy Morrow",7206),
  (188,"Inga Horn",2639),
  (189,"MacKenzie Wooten",2163),
  (190,"Lee Holland",6859),
  (191,"Leroy White",3249),
  (192,"Rahim Macdonald",6886),
  (193,"Bo Galloway",5739),
  (194,"Michael Ayala",8043),
  (195,"Stella Booth",5521),
  (196,"Felicia Serrano",6331),
  (197,"Kasper Weaver",7893),
  (198,"Irma Gordon",7173),
  (199,"Diana Rocha",2312),
  (200,"Cody Schmidt",8833);
INSERT INTO `products` (`product_id`,`name`,`price`)
VALUES
  (201,"Camille Fuller",9162),
  (202,"Malachi Kim",3104),
  (203,"Farrah Mitchell",5071),
  (204,"Elizabeth Burt",2910),
  (205,"Guinevere White",1326),
  (206,"Lane Sullivan",1939),
  (207,"Kessie Gonzalez",4844),
  (208,"Summer Rivers",4005),
  (209,"Sybill Anthony",2119),
  (210,"Uriel Rutledge",1708),
  (211,"Nolan Little",6893),
  (212,"Wyatt Mccormick",7314),
  (213,"Dolan Foley",8054),
  (214,"Erica Love",8411),
  (215,"Joan Coleman",3217),
  (216,"Kuame Peck",4141),
  (217,"Damian Swanson",9938),
  (218,"Norman Estrada",7132),
  (219,"Martha Russo",6601),
  (220,"Wynter Mcmahon",2932),
  (221,"Hoyt Cash",8853),
  (222,"Salvador Kaufman",6431),
  (223,"Virginia Irwin",1207),
  (224,"Patricia Blankenship",6177),
  (225,"Cleo Pratt",4630),
  (226,"Shelley Everett",8563),
  (227,"Abdul Walton",6486),
  (228,"Kim Horn",9364),
  (229,"Bianca Mccall",5387),
  (230,"Cecilia Mcclain",5414),
  (231,"Otto Curtis",997),
  (232,"Gabriel Dillon",4543),
  (233,"Jackson Rosa",7544),
  (234,"Steel Silva",3985),
  (235,"Dylan Dodson",4320),
  (236,"Karina Dalton",9856),
  (237,"Maite Lawrence",5370),
  (238,"Barry Boyd",5618),
  (239,"Carlos Goodwin",2241),
  (240,"Jacob Holland",5928),
  (241,"Penelope Whitney",1247),
  (242,"Adam Lucas",8344),
  (243,"Marsden Frost",8464),
  (244,"Isabelle Holden",8331),
  (245,"Stone Garrison",2385),
  (246,"Galena Herring",9301),
  (247,"Avye Weaver",6388),
  (248,"Otto Brooks",9962),
  (249,"Duncan Sexton",4522),
  (250,"Jarrod Bowers",8865);
INSERT INTO `products` (`product_id`,`name`,`price`)
VALUES
  (251,"Elmo Rios",9044),
  (252,"Piper Nguyen",7587),
  (253,"Melanie Bright",1957),
  (254,"Demetrius Stout",8694),
  (255,"Shay Clemons",3253),
  (256,"Wynne Hunter",7210),
  (257,"Hollee Greer",8784),
  (258,"Oliver Webb",6404),
  (259,"Mariko Wilcox",7403),
  (260,"Aurelia Russo",4682),
  (261,"Barrett Frye",5799),
  (262,"Callie Dalton",3791),
  (263,"Ivan Gilliam",7823),
  (264,"Grace Garcia",7179),
  (265,"Scott Foreman",7297),
  (266,"Murphy Suarez",7969),
  (267,"Nyssa Koch",7710),
  (268,"Herman Castro",812),
  (269,"Gregory Ross",8511),
  (270,"Noelani Espinoza",563),
  (271,"Dorian Maldonado",5261),
  (272,"Audra Hebert",4362),
  (273,"Aimee Serrano",6889),
  (274,"Maisie Montgomery",649),
  (275,"Moses Parrish",9174),
  (276,"Mara Schroeder",7437),
  (277,"Hamilton Kirk",4962),
  (278,"Fleur Andrews",505),
  (279,"Anthony Boone",5125),
  (280,"Vivien Harrison",7103),
  (281,"Georgia Jimenez",2536),
  (282,"Caldwell Harding",3276),
  (283,"Buffy Goff",4283),
  (284,"Steven Hernandez",8647),
  (285,"Regina Foley",8456),
  (286,"Yoshi Petty",7692),
  (287,"Alisa Williamson",1827),
  (288,"Karyn Guthrie",8906),
  (289,"Bernard Kaufman",4659),
  (290,"Jenette Mack",8246),
  (291,"Fuller Morris",4631),
  (292,"Iona Gibbs",7512),
  (293,"Fatima Pruitt",680),
  (294,"Colorado David",2132),
  (295,"Ebony Nunez",4004),
  (296,"Aristotle Prince",2528),
  (297,"Martha Langley",3852),
  (298,"Raya Cochran",7088),
  (299,"Rachel Livingston",9308),
  (300,"Lucas Walsh",9210);
INSERT INTO `products` (`product_id`,`name`,`price`)
VALUES
  (301,"Clare Weber",3940),
  (302,"Malachi Fuentes",7790),
  (303,"Kenneth Ewing",4605),
  (304,"Moana Henderson",6977),
  (305,"Orson Bowman",7326),
  (306,"Raja Finley",2749),
  (307,"Keane Solis",2335),
  (308,"Peter Holloway",7072),
  (309,"Owen Valdez",6627),
  (310,"Madaline Ray",2002),
  (311,"Pamela Cline",6570),
  (312,"Kane Mitchell",1711),
  (313,"Felix Fletcher",6296),
  (314,"Declan Rutledge",8909),
  (315,"Blake Bennett",3235),
  (316,"Ann Ballard",2066),
  (317,"Anne Austin",8271),
  (318,"Heidi Haley",6066),
  (319,"Illana Monroe",9700),
  (320,"Brynn Meyers",6022),
  (321,"Wyatt Ratliff",1009),
  (322,"Quail Prince",3664),
  (323,"Clio Guzman",8968),
  (324,"Anjolie Ellison",3605),
  (325,"Bruno Lott",8909),
  (326,"Jonas Aguilar",3561),
  (327,"Jerome Webster",2087),
  (328,"Erica Nixon",5928),
  (329,"Zenia Kline",5176),
  (330,"Brody Craft",3023),
  (331,"Oprah Battle",7940),
  (332,"Rachel Warner",3946),
  (333,"Evelyn Dawson",3848),
  (334,"Ainsley Fitzpatrick",9036),
  (335,"Aristotle Hancock",2586),
  (336,"Kenneth Turner",4753),
  (337,"Kamal Dudley",8925),
  (338,"Leo Wilder",6018),
  (339,"Jermaine Dominguez",7356),
  (340,"Wayne Alexander",7918),
  (341,"Shea Williams",7449),
  (342,"Brandon Whitehead",730),
  (343,"Mariam Kelly",5785),
  (344,"Aspen Fischer",9127),
  (345,"Fredericka Long",6201),
  (346,"Illiana Mueller",3467),
  (347,"Minerva Hebert",6479),
  (348,"Timon Washington",7814),
  (349,"Rachel Coffey",1518),
  (350,"Tatiana Lamb",3520);
INSERT INTO `products` (`product_id`,`name`,`price`)
VALUES
  (351,"Ria Murray",1293),
  (352,"Hedwig Sargent",1517),
  (353,"Paul Lambert",8714),
  (354,"Edward Perkins",6407),
  (355,"Lee Beard",3308),
  (356,"Maia Lowery",5568),
  (357,"Rinah Byrd",2144),
  (358,"Yuri Solis",8202),
  (359,"Joan Blake",5597),
  (360,"Jacob Maldonado",4429),
  (361,"Holmes Gilliam",4834),
  (362,"Kelly Dale",1285),
  (363,"Iliana Hahn",957),
  (364,"Jasper Foreman",2760),
  (365,"Eagan Chambers",3460),
  (366,"Cade Sheppard",7130),
  (367,"Nomlanga Hayden",541),
  (368,"Lesley Rutledge",3930),
  (369,"Elton Petty",7554),
  (370,"Teegan Jefferson",3952),
  (371,"Sydney Carney",2019),
  (372,"Phelan O'donnell",1861),
  (373,"Marshall Hebert",8026),
  (374,"Brooke Sutton",4086),
  (375,"MacKensie O'donnell",2022),
  (376,"Shelly Frank",1013),
  (377,"Xanthus Crawford",576),
  (378,"Damian Wolfe",1779),
  (379,"Kylie Riley",3227),
  (380,"Glenna Wooten",6456),
  (381,"Honorato Berry",7588),
  (382,"Lilah Jackson",5512),
  (383,"Nathan Nicholson",1279),
  (384,"Hamilton Leon",7565),
  (385,"Rahim Nieves",8910),
  (386,"Philip Hamilton",4469),
  (387,"Vera Morse",3267),
  (388,"Allegra Albert",3207),
  (389,"Teagan Lowe",2094),
  (390,"Ferris Pena",6765),
  (391,"Adele Peck",7342),
  (392,"Lionel Whitley",6861),
  (393,"Evangeline Brooks",1875),
  (394,"Hanna Stanley",4407),
  (395,"Evelyn Olson",2751),
  (396,"Colin Dillon",956),
  (397,"Erasmus Erickson",3215),
  (398,"Renee Solis",9025),
  (399,"Nell Farmer",2605),
  (400,"Keefe Battle",2772);
INSERT INTO `products` (`product_id`,`name`,`price`)
VALUES
  (401,"Jescie Morin",957),
  (402,"Molly Bolton",6793),
  (403,"Keelie Sykes",8939),
  (404,"Nash Boyle",7666),
  (405,"Christian Dominguez",2153),
  (406,"Inez Wheeler",3189),
  (407,"Hector Baird",3441),
  (408,"Skyler Ratliff",3232),
  (409,"Giacomo Walton",8974),
  (410,"Lydia Rowe",6113),
  (411,"Echo Bauer",3934),
  (412,"Ursula Fields",8826),
  (413,"Lucian Whitaker",9834),
  (414,"Kuame Carver",6821),
  (415,"Daniel Ingram",6924),
  (416,"Ifeoma Cabrera",5270),
  (417,"Hasad Rodriguez",5454),
  (418,"Ori Dudley",8405),
  (419,"May Duncan",4075),
  (420,"Simon Chang",8966),
  (421,"Kasper Dickerson",4902),
  (422,"Raya Burt",4453),
  (423,"Anika Nixon",8042),
  (424,"Fay Beck",5700),
  (425,"Caryn Short",5681),
  (426,"Shana Miller",6376),
  (427,"Logan Whitfield",7215),
  (428,"Claudia Good",7677),
  (429,"Deacon Pate",3758),
  (430,"Jameson Sullivan",7820),
  (431,"Reece Duffy",8863),
  (432,"Yael Fleming",897),
  (433,"Ezekiel Morrow",9831),
  (434,"Hiroko Nichols",1664),
  (435,"Sawyer Flores",5035),
  (436,"Stuart Barnes",7869),
  (437,"Roary Stewart",7511),
  (438,"Jared Cain",8732),
  (439,"Madonna Solis",4430),
  (440,"Cairo Hughes",4394),
  (441,"John Doyle",8157),
  (442,"Richard Lindsay",4689),
  (443,"Patience Bond",7781),
  (444,"Russell Mendoza",2056),
  (445,"Brendan Joyce",992),
  (446,"Nicole Rich",2007),
  (447,"Amery Higgins",909),
  (448,"Nash Ferrell",3642),
  (449,"Guy Allison",4047),
  (450,"Chase Russell",5516);
INSERT INTO `products` (`product_id`,`name`,`price`)
VALUES
  (451,"Warren Brady",8027),
  (452,"Rudyard Sutton",2145),
  (453,"Ethan Mayo",544),
  (454,"Melyssa Collins",4567),
  (455,"Neville Holloway",3008),
  (456,"Keegan Combs",7561),
  (457,"Paula Garrison",3259),
  (458,"Cassidy Salinas",1508),
  (459,"Gwendolyn Wilkinson",786),
  (460,"Dustin O'brien",1576),
  (461,"Alden Molina",9474),
  (462,"Xantha Melendez",9311),
  (463,"Baker Rojas",605),
  (464,"Pamela Winters",6617),
  (465,"Fredericka Daugherty",3824),
  (466,"Jena Morin",7757),
  (467,"Zena West",5979),
  (468,"Cooper Wolfe",1769),
  (469,"Channing Schneider",2304),
  (470,"Holmes Baxter",3433),
  (471,"Forrest Foley",9542),
  (472,"Kibo Davis",6563),
  (473,"Tate Nixon",6276),
  (474,"Devin Talley",8691),
  (475,"Lyle Landry",1846),
  (476,"David Case",8645),
  (477,"Daryl Everett",3612),
  (478,"Bernard Mccoy",7736),
  (479,"Steven Vazquez",4206),
  (480,"Olga Blackwell",8011),
  (481,"Shafira Hood",3794),
  (482,"Rafael Avila",7485),
  (483,"Guy Gordon",4871),
  (484,"Thane Albert",5374),
  (485,"Rigel Coffey",5270),
  (486,"Hollee Graves",5046),
  (487,"Zena Cummings",2149),
  (488,"Fulton Cooke",7834),
  (489,"Jerome Hogan",8802),
  (490,"Kieran Mccoy",3918),
  (491,"Byron Bruce",655),
  (492,"Sydney Hill",4710),
  (493,"Kevin Hansen",9674),
  (494,"Nevada Snyder",7765),
  (495,"Ferdinand Hayden",3552),
  (496,"Beatrice Powell",1175),
  (497,"Eric Mckee",3658),
  (498,"Petra Lester",4562),
  (499,"Melinda Maldonado",3219),
  (500,"Iliana Padilla",8138);
