-- update 1/8/2023 by Nhatphat112
-- add table : role
-- alter user : add column role_id
-- update insert role, user
drop database if exists boutique;
create database if not exists boutique;
use boutique;
create table if not exists user(
	id bigint auto_increment,
	username varchar(255) not null,
	password varchar(255) not null,
	email varchar (255) not null,
	role_id bigint not null,
	primary key (id)
	
);
CREATE table if not exists phone(
	id bigint auto_increment,
	phone_number varchar(15),
	user_id bigint,
	primary key(id)
);
CREATE table if not exists status(
	id bigint auto_increment,
	name nvarchar(255),
	primary key(id)
);
create table if not exists cart(
	id bigint auto_increment,
	stock_id bigint,
	user_id bigint,
	quantity int,
	primary key(id)
);
create table if not exists category(
	id bigint auto_increment,
	name nvarchar(255),
	primary key(id)
);
create table if not exists order_detail(
	id bigint auto_increment,
	stock_id bigint,
	order_id bigint,
 	user_id bigint,
	price double,
	quantity int,
	primary key(id)
	
);
create table if not exists product(
	id bigint auto_increment,
	name nvarchar(255),
	category_id bigint,
	image varchar(255),
	sold_quantity int,
	description text,
	primary key(id)
);
create table if not exists review(
	id bigint auto_increment,
	user_id bigint,
	product_id bigint,
	content text(1000),
	appriciation smallint,
	primary key(id)
);
create table if not exists u_order(
	id bigint auto_increment,
	user_id bigint,
	address_id bigint,
	status_id bigint,
	phone_id bigint,
	phone_number varchar(15),
	address_detail nvarchar(255),
	total double,
	primary key(id)
);

create table if not exists stock(
	id bigint auto_increment,
	color_id bigint,
	quantity int,
	product_id bigint,
	price double,
	image varchar(255),
	primary key (id)
	
);
create table if not exists address(
	id bigint auto_increment,
	city_province_id bigint,
	country_id bigint,
	fee double,
	user_id bigint,
	detail nvarchar(255),
	primary key(id)
);
create table if not exists color(
	id bigint auto_increment,
	name nvarchar(255),
	primary key(id)
);
create table if not exists city_province(
	id bigint auto_increment,
	name nvarchar(255),
	city varchar(10),
	primary key (id)
);
create table if not exists country(
	id bigint auto_increment,
	name nvarchar(255),
	primary key(id)
);
/*
CREATE table if not exists counpon(
	id bigint auto_increment,
	code varchar(255),
	description nvarchar(255),
	primary key (id);
);

CREATE table if not exists order_counpon(
	order_id bigint not null,
	counpon_id bigint not null,
	primary key (order_id,counpon_id)
);
*/

create TABLE if not exists role(
	id bigint auto_increment,
	name varchar(255),
	description nvarchar(255),
	primary key(id)
);
alter table order_detail
add constraint FK_order_detail_stock_id foreign key(stock_id) references stock(id)
on update cascade
on delete cascade;
alter table user
add constraint FK_user_role_id foreign key(role_id) references role(id)
on update cascade
on delete cascade;
alter table order_detail
add constraint FK_order_detail_order_id foreign key(order_id) references u_order(id)
on update cascade
on delete cascade;
alter table product
add constraint FK_product_category_id foreign key(category_id) references category(id)
on update cascade
on delete cascade;
alter table u_order
add constraint FK_u_order_user_id foreign key(user_id) references user(id)
on update cascade
on delete cascade;

alter table stock
add constraint FK_stock_color_id foreign key(color_id) references color(id)
on update cascade
on delete cascade;
alter table stock
add constraint FK_stock_product_id foreign key(product_id) references product(id)
on update cascade
on delete cascade;
alter table cart
add constraint FK_cart_stock_id foreign key(stock_id) references stock(id)
on update cascade
on delete cascade;
alter table cart
add constraint FK_cart_user_id foreign key(user_id) references user(id)
on update cascade
on delete cascade;

 alter table address
add constraint FK_address_city_province_id foreign key(city_province_id) references city_province(id)
on update cascade
on delete cascade;
alter table address

add constraint FK_address_country_id foreign key(country_id) references country(id)
on update cascade
on delete cascade;
alter table address
add constraint FK_address_user_id foreign key(user_id) references user(id)
on update cascade
on delete cascade;
alter table review
add constraint FK_review_user_id foreign key(user_id) references user(id)
on update cascade
on delete cascade;
alter table review
add constraint FK_review_product_id foreign key(product_id) references product(id)
on update cascade
on delete cascade;
alter table u_order
add constraint FK_u_order_status_id foreign key(status_id) references status(id)
on update cascade
on delete cascade;
alter table phone
add constraint FK_phone_user_id foreign key(user_id) references user(id)
on update cascade
on delete cascade;

alter table order_detail
add constraint FK_order_detail_user_id foreign key(user_id) references user(id)
on update cascade
on delete cascade;

 


/*alter table order_counpon
add constraint FK_order_counpon_order_id foreign key(order_id) references order(id)
on update cascade
on delete cascade;
alter table order
alter table order_counpon
add constraint FK_order_detail_counpon_id foreign key(counpon_id ) references counpon(id)
on update cascade
on delete cascade;
alter table order 
*/


INSERT INTO boutique.category (name) VALUES
	 ('Watches'),
	 ('Cameras'),
	 ('Headphones'),
	 ('Speaker');


INSERT INTO boutique.color (name) VALUES
	 ('Black'),
	 ('White'),
	 ('Red'),
	 ('Yellow'),
	 ('Gray'),
	 ('Blue'),
	 ('Cream'),
	 ('Brown'),
	 ('Pink'),
	 ('Teal');
INSERT INTO boutique.color (name) VALUES
	 ('Silver'),
     ('Green');


INSERT INTO boutique.product (name,category_id,image,sold_quantity,description) VALUES
	 ('OontZ Angle 3 Bluetooth',4,'OontZAngleBlack.jpg',108,'The OontZ Angle 3 Bluetooth Speaker is a budget-friendly option that delivers surprisingly high-quality sound for its price range. With its compact, portable design, it''s great for on-the-go listening and features a long battery life of up to 14 hours. The speaker is also water-resistant, making it perfect for outdoor use. The Angle 3 has Bluetooth connectivity and even supports hands-free calling with its built-in microphone. Overall, the OontZ Angle 3 is a solid choice for those looking for an affordable, portable Bluetooth speaker.'),
	 ('Audio Pro A10 Wireless',4,'AudioProBlack.jpg',197,'The Audio Pro A10 Wireless Speaker is a versatile, modern-designed speaker with full, powerful sound. With Bluetooth and Wi-Fi connectivity, it can stream music from multiple sources and supports features such as AirPlay 2 and Spotify Connect. The Audio Pro A10 can also be connected with other speakers to create a multi-room audio system.'),
	 ('JBL Go',4,'JBLGoBlack.jpg',341,'The JBL Go is a compact, portable Bluetooth speaker that delivers surprisingly powerful sound for its size. With a rechargeable battery that lasts up to 5 hours, it''s great for on-the-go listening. The speaker also features a built-in noise-cancelling speakerphone for taking calls and a durable, splashproof design.'),
	 ('Marshall Emberton',4,'MarshallEmbertonBlack.jpg',234,'The Marshall Emberton is a small, portable Bluetooth speaker that packs a big punch with its rich, powerful sound and impressive battery life of up to 20 hours. With its stylish design and durable construction, the speaker is perfect for taking on the go. It''s also water-resistant and features a multi-directional control knob for easy operation.'),
	 ('Marshall Stanmore',4,'MarshallStanmoreBlack.jpg',240,'The Marshall Stanmore is a classic-looking Bluetooth speaker that delivers rich, powerful sound with deep bass and crisp, clear highs. With its vintage design and iconic Marshall branding, it''s a stylish addition to any room. The speaker also features multiple connectivity options, including Bluetooth, RCA, and AUX, and can be paired with other speakers to create a multi-room audio system. Additionally, the Stanmore has custom controls for adjusting bass and treble levels to suit your preferences.'),
	 ('Edifirer',3,'Edifirer.png',123,'Edifier over-ear headphones provide an immersive audio experience with excellent noise isolation. The soft, cushioned ear cups and adjustable headband ensure a comfortable fit for extended listening sessions. With a stylish, sleek design and high-quality sound, these headphones are perfect for music lovers and audiophiles alike.'),
	 ('Bose SoundLink Revolve II',4,'BoseSoundLinkRevolveBlack.jpg',65,'SoundLink Revolve II, one of our best-performing portable Bluetooth speakers yet. It delivers true 360-degree sound for consistent, uniform coverage. Place it in the center of the room and everyone gets the same experience. Or set it near a wall and sound will radiate and reflect around the room.'),
	 ('KODAK PIXPRO FZ55',2,'KodakPixpro5XBlack.jpg',54,'The Kodak PIXPRO FZ55 Digital Camera is a small and easy-to-use Friendly Zoom camera from the Kodak PIXPRO collection. This point-and-shoot camera features a 28mm wide angle lens with a 16MP 1/2.3" CMOS sensor and 2.7" LCD screen.'),
	 ('Fujifilm Finepix J10',2,'FujifilmFinepixJ10Black.jpg',122,'The Fujifilm FinePix J10 Digital Camera is an affordable digital camera that incorporates an 8.2 Megapixel CCD sensor, a 2.5" LCD and a 3x Fujinon optical zoom lens - all in a compact body that is a mere 22 mm thick.'),
	 ('Bulova Men''s Military',1,'BulovaMenMilitaryBlack.jpg',65,'Bulova Men''s Military Heritage Hack Stainless Steel 3-Hand Automatic Watch, NATO Leather Strap, Luminous Hands and Markers');
INSERT INTO boutique.product (name,category_id,image,sold_quantity,description) VALUES
	 ('Citizen Promaster Dive',1,'CitizenPromasterDiveBlack.jpg',244,'It features a one-way rotating bezel, and is anti-shock and anti-reflective, making this Citizen dive watch as practical as it is stylish. And the watch is sustainably powered by light with the exclusive Citizen Eco-Drive technology, so there are no worries about a dead battery while exploring the waters of the world.'),
	 ('Fossil Grant',1,'FossilGrantBlack.jpg',21,'The Grant collection is always in style thanks to its time-honored design. Modern Roman numeral markers and materials like stainless steel make this a watch you''ll wear for years to come'),
	 ('JBL Tune 510BT',3,'JBLTune510BTBlack.jpg',45,'The JBL Tune 510BT headphones let you stream powerful JBL Pure Bass sound with no strings attached. Easy to use, these headphones provide up to 40 hours of pure pleasure and an extra 2 hours of battery with just 5 minutes of power with the USB-C charging cable. And if a call comes in while you are watching a video on another device, the JBL Tune 510BT seamlessly switches to your mobile. Bluetooth 5.0 enabled and designed to be comfortable, the JBL Tune 510BT headphones also allow you to connect to Siri or Google without using your mobile device.'),
	 ('Orient RA-AK00',1,'OrientRA-AK00Black.jpg',242,'A hand-wound crown with automatic self-winding functionality completes the watch''s alluring design. The RA-AK00 is protected by curved sapphire glass and supports water resistance of 50 meters.'),
	 ('Sony WH-1000XM4',3,'SonyWH-1000XM4Black.jpg',43,'The Sony WH-1000XM4 are the successor to the Sony WH-1000XM3 Wireless, coming with a longer continuous battery life, additional touch-sensitive control features, and multi-device pairing. Like their predecessor, they have an outstanding noise isolation performance thanks to their ANC feature and a comfortable, premium-looking design.'),
	 ('KODAK PIXPRO AZ405',2,'KodakPixproBlack.jpg',78,'The PIXPRO AZ405 Digital Camera from Kodak is distinguished by a long-reaching 40x zoom along with versatile recording capabilities. A 20MP CCD sensor enables recording high-resolution stills and HD 720p video, as well as an expandable sensitivity range from ISO 80-3200 for working in a variety of lighting conditions.'),
	 ('Bose QuietComfort 45',3,'BoseQuietComfort45Black.jpg',197,'Bose QuietComfort 45 headphones also deliver proprietary acoustic technology for deep, clear audio with adjustable EQ so you can tune your music to your liking. They''re wireless headphones with a perfect balance of quiet, comfort, and sound you''ll enjoy all day long. Noise cancelling, perfected.'),
	 ('Sony Cyber-Shot',2,'SonyDigitalCameraBlack.jpg',105,'With a compact design small enough to fit in your pocket, the W830 is packed with features including a 20.1MP sensor, ZEISS lens with 8x optical zoom, fast autofocus, and Optical SteadyShot™ image stabilisation. Effortlessly capture the beauty in every scene with W830 Cyber-shot™.');

INSERT INTO boutique.stock (color_id,quantity,product_id,price,image) VALUES
	 (1,10,1,28.0,'OontZAngleBlack.jpg'),
	 (6,0,1,28.0,'OontZAngleBlue.jpg'),
	 (3,23,1,28.0,'OontZAngleRed.jpg'),
	 (2,51,1,28.0,'OontZAngleWhite.jpg'),
	 (1,65,2,250.0,'AudioProBlack.jpg'),
	 (2,45,2,250.0,'AudioProWhite.jpg'),
	 (1,20,3,25.0,'JBLGoBLack.jpg'),
	 (6,26,3,25.0,'JBLGoBlue.jpg'),
	 (5,0,3,25.0,'JBLGoGray.jpg'),
	 (3,43,3,25.0,'JBLGoRed.jpg');
INSERT INTO boutique.stock (color_id,quantity,product_id,price,image) VALUES
	 (1,54,4,168.0,'MarshallEmbertonBlack.jpg'),
	 (7,13,4,169.0,'MarshallEmbertonCream.jpg'),
	 (1,0,5,379.0,'MarshallStanmoreBlack.jpg'),
	 (8,33,5,379.0,'MarshallStanmoreBrown.jpg'),
	 (7,32,5,379.0,'MarshallStanmoreCream.jpg'),
	 (1,10,6,400.0,'Edifirer.png'),
	 (6,12,6,400.0,'EdifirerBlue.png'),
	 (1,54,7,219.0,'BoseSoundLinkRevolveBlack.jpg'),
	 (2,32,7,219.0,'BoseSoundLinkRevolveWhite.jpg'),
	 (1,75,8,99.0,'KodakPixpro5XBlack.jpg');
INSERT INTO boutique.stock (color_id,quantity,product_id,price,image) VALUES
	 (3,12,8,99.0,'KodakPixpro5XRed.jpg'),
	 (6,34,8,99.0,'KodakPixpro5XBlue.jpg'),
	 (1,63,9,129.0,'FujifilmFinepixJ10Black.jpg'),
	 (11,13,9,129.0,'FujifilmFinepixJ10Silver.jpg'),
	 (1,64,10,192.0,'BulovaMenMilitaryBlack.jpg'),
	 (12,24,10,201.0,'BulovaMenMilitaryGreen.jpg'),
	 (1,53,11,248.0,'CitizenPromasterDiveBlack.jpg'),
	 (6,23,11,193.0,'CitizenPromasterDiveBlue.jpg'),
	 (10,12,11,316.0,'CitizenPromasterDiveTeal.jpg'),
	 (1,47,12,12.0,'FossilGrantBlack.jpg');
INSERT INTO boutique.stock (color_id,quantity,product_id,price,image) VALUES
	 (6,71,12,12.0,'FossilGrantBlue.jpg'),
	 (8,13,12,14.0,'FossilGrantBrown.jpg'),
	 (1,14,13,20.0,'JBLTune510BTBlack.jpg'),
	 (6,45,13,20.0,'JBLTune510BTBlue.jpg'),
	 (9,35,13,20.0,'JBLTune510BTPink.jpg'),
	 (2,12,13,20.0,'JBLTune510BTWhite.jpg'),
	 (1,17,14,266.0,'OrientRA-AK00Black.jpg'),
	 (6,83,14,296.0,'OrientRA-AK00Blue.jpg'),
	 (8,76,14,296.0,'OrientRA-AK00Brown.jpg'),
	 (1,13,15,348.0,'SonyWH-1000XM4Black.jpg');
INSERT INTO boutique.stock (color_id,quantity,product_id,price,image) VALUES
	 (6,2,15,237.0,'SonyWH-1000XM4Blue.jpg'),
	 (11,65,15,348.0,'SonyWH-1000XM4Silver.jpg'),
	 (1,64,16,189.0,'KodakPixproBlack.jpg'),
	 (2,0,16,185.0,'KodakPixproWhite.jpg'),
	 (1,43,17,239.0,'BoseQuietComfort45Black.jpg'),
	 (2,23,17,247.0,'BoseQuietComfort45White.jpg'),
	 (6,27,17,263.0,'BoseQuietComfort45Blue.jpg'),
	 (1,54,18,166.0,'SonyDigitalCameraBlack.jpg'),
	 (11,86,18,176.0,'SonyDigitalCameraSilver.jpg');

INSERT Into `role` (name,description)
values(
	"ADMIN",
	null
);
INSERT Into `role` (name,description)
values(
	"USER",
	null
);

INSERT INTO boutique.user (username,password,email,role_id) VALUES
	 ('nguyenvana','$2y$10$d6ickSLQRa/xbPRi4APIYubydZs2adm2clG3AwkC4AAnW4MxomkQy','nguyenvana@gmail.com',1),
	 ('nguyenvanb','$2y$10$loCexWfgG1ZNNXule6cCzuNUKOmQdnJFtvaqLcx6MQ0kkYygEoBJq','nguyenvanb@gmail.com',2),
	 ('nguyenvanc','$2y$10$5R9nyHc7soXBk./3pkYQIe.lZhOz8E2yxjLSGjT9BK7.gPlE2/Kq6','nguyenvanc@gmail.com',1);


INSERT INTO city_province (name, city)
VALUES
    ('An Giang', 'no'),
    ('Bà Rịa - Vũng Tàu', 'no'),
    ('Bắc Giang', 'no'),
    ('Bắc Kạn', 'no'),
    ('Bạc Liêu', 'no'),
    ('Bắc Ninh', 'no'),
    ('Bến Tre', 'no'),
    ('Bình Định', 'no'),
    ('Bình Dương', 'no'),
    ('Bình Phước', 'no'),
    ('Bình Thuận', 'no'),
    ('Cà Mau', 'no'),
    ('Cần Thơ', 'yes'),
    ('Cao Bằng', 'no'),
    ('Đà Nẵng', 'yes'),
    ('Đắk Lắk', 'no'),
    ('Đắk Nông', 'no'),
    ('Điện Biên', 'no'),
    ('Đồng Nai', 'no'),
    ('Đồng Tháp', 'no'),
    ('Gia Lai', 'no'),
    ('Hà Giang', 'no'),
    ('Hà Nam', 'no'),
    ('Hà Nội', 'yes'),
    ('Hà Tĩnh', 'no'),
    ('Hải Dương', 'no'),
    ('Hải Phòng', 'yes'),
    ('Hậu Giang', 'no'),
    ('Hòa Bình', 'no'),
    ('Hưng Yên', 'no'),
    ('Khánh Hòa', 'no'),
    ('Kiên Giang', 'no'),
    ('Kon Tum', 'no'),
    ('Lai Châu', 'no'),
    ('Lâm Đồng', 'no'),
    ('Lạng Sơn', 'no'),
    ('Lào Cai', 'no'),
    ('Long An', 'no'),
    ('Nam Định', 'no'),
    ('Nghệ An', 'no'),
    ('Ninh Bình', 'no'),
    ('Ninh Thuận', 'no'),
    ('Phú Thọ', 'no'),
    ('Phú Yên', 'no'),
    ('Quảng Bình', 'no'),
    ('Quảng Nam', 'no'),
    ('Quảng Ngãi', 'no'),
    ('Quảng Ninh', 'no'),
    ('Quảng Trị', 'no'),
    ('Sóc Trăng', 'no'),
    ('Sơn La', 'no'),
    ('Tây Ninh', 'no'),
    ('Thái Bình', 'no'),
    ('Thái Nguyên', 'no'),
    ('Thanh Hóa', 'no'),
    ('Thừa Thiên Huế', 'no'),
    ('Tiền Giang', 'no'),
    ('TP. Hồ Chí Minh', 'yes'),
    ('Trà Vinh', 'no'),
    ('Tuyên Quang', 'no'),
    ('Vĩnh Long', 'no'),
    ('Vĩnh Phúc', 'no'),
    ('Yên Bái', 'no');
   -- default
  INSERT INTO  city_province (name, city)
values(
	"",
	"no"
);

INSERT INTO country (name) VALUES
('Afghanistan'),
('Albania'),
('Algeria'),
('Andorra'),
('Angola'),
('Antigua and Barbuda'),
('Argentina'),
('Armenia'),
('Australia'),
('Austria'),
('Azerbaijan'),
('Bahamas'),
('Bahrain'),
('Bangladesh'),
('Barbados'),
('Belarus'),
('Belgium'),
('Belize'),
('Benin'),
('Bhutan'),
('Bolivia'),
('Bosnia and Herzegovina'),
('Botswana'),
('Brazil'),
('Brunei'),
('Bulgaria'),
('Burkina Faso'),
('Burundi'),
('Cambodia'),
('Cameroon'),
('Canada'),
('Cape Verde'),
('Central African Republic'),
('Chad'),
('Chile'),
('China'),
('Colombia'),
('Comoros'),
('Costa Rica'),
('Croatia'),
('Cuba'),
('Cyprus'),
('Czech Republic'),
('Democratic Republic of the Congo'),
('Denmark'),
('Djibouti'),
('Dominica'),
('Dominican Republic'),
('East Timor'),
('Ecuador'),
('Egypt'),
('El Salvador'),
('Equatorial Guinea'),
('Eritrea'),
('Estonia'),
('Ethiopia'),
('Fiji'),
('Finland'),
('France'),
('Gabon'),
('Gambia'),
('Georgia'),
('Germany'),
('Ghana'),
('Greece'),
('Grenada'),
('Guatemala'),
('Guinea'),
('Guinea-Bissau'),
('Guyana'),
('Haiti'),
('Honduras'),
('Hungary'),
('Iceland'),
('India'),
('Indonesia'),
('Iran'),
('Iraq'),
('Ireland'),
('Israel'),
('Italy'),
('Ivory Coast'),
('Jamaica'),
('Japan'),
('Jordan'),
('Kazakhstan'),
('Kenya'),
('Kiribati'),
('Kuwait'),
('Kyrgyzstan'),
('Laos'),
('Latvia'),
('Lebanon'),
('Lesotho'),
('Liberia'),
('Libya'),
('Liechtenstein'),
('Lithuania'),
('Luxembourg'),
('Macedonia'),
('Madagascar'),
('Malawi'),
('Malaysia'),
('Maldives'),
('Mali'),
('Malta'),
('Marshall Islands'),
('Mauritania'),
('Mauritius'),
('Mexico'),
('Micronesia'),
('Moldova'),
('Monaco'),
('Mongolia'),
('Montenegro'),
('Morocco'),
('Mozambique'),
('Myanmar'),
('Namibia'),
('Nauru'),
('Nepal'),
('Netherlands'),
('New Zealand'),
('Nicaragua'),
('Niger'),
('Nigeria'),
('North Korea'),
('Norway'),
('Oman'),
('Pakistan'),
('Palau'),
('Panama'),
('Papua New Guinea'),
('Paraguay'),
('Peru'),
('Philippines'),
('Poland'),
('Portugal'),
('Qatar'),
('Republic of the Congo'),
('Romania'),
('Russia'),
('Rwanda'),
('Saint Kitts and Nevis'),
('Saint Lucia'),
('Saint Vincent and the Grenadines'),
('Samoa'),
('San Marino'),
('Sao Tome and Principe'),
('Saudi Arabia'),
('Senegal'),
('Serbia'),
('Seychelles'),
('Sierra Leone'),
('Singapore'),
('Slovakia'),
('Slovenia'),
('Solomon Islands'),
('Somalia'),
('South Africa'),
('South Korea'),
('South Sudan'),
('Spain'),
('Sri Lanka'),
('Sudan'),
('Suriname'),
('Swaziland'),
('Sweden'),
('Switzerland'),
('Syria'),
('Tajikistan'),
('Tanzania'),
('Thailand'),
('Togo'),
('Tonga'),
('Trinidad and Tobago'),
('Tunisia'),
('Turkey'),
('Turkmenistan'),
('Tuvalu'),
('Uganda'),
('Ukraine'),
('United Arab Emirates'),
('United Kingdom'),
('United States of America'),
('Uruguay'),
('Uzbekistan'),
('Vanuatu'),
('Vatican City'),
('Venezuela'),
('Việt Nam'),
('Yemen'),
('Zambia'),
('Zimbabwe');
INSERT INTO boutique.address (city_province_id,country_id,fee,user_id,detail) VALUES
	 (58,191,0.0,1,'23 Tô Hiến Thành, Phường 12, Quận 10'),
	 (58,191,0.0,1,'20 Khánh Hội, Phường 3, Quận 4'),
	 (58,191,0.0,2,'12/26 Hoàng Diệu, Phường 2, Quận Tân Bình'),
	 (58,191,0.0,3,'20 Nguyễn Hữu Thọ, Phường Tân Hưng, Quận 7'),
	 (58,191,0.0,2,'125 Nguyễn Trãi, Phường Bến Thành, Quận 1'),
	 (58,191,0.0,3,'278 Nguyễn Bỉnh Khiêm, Phường 22, Quận Bình Thạnh');

INSERT INTO status (name)
values(
	"delivering"
);
INSERT INTO status (name)
values(
	"delivered"
);
INSERT INTO boutique.phone (phone_number,user_id) VALUES
	 ('+84350434589',1),
	 ('+84323456022',1),
	 ('+84350434589',2),
	 ('+84354562022',2),
	 ('+84435782022',3),
	 ('+84789434589',3);
INSERT INTO boutique.review (user_id,product_id,content,appriciation) VALUES
	 (2,1,'Sản phẩm rất tốt, tôi rất hài lòng',5),
	 (1,1,'Sản phẩm phù hợp với giá tiền, không quá xuất sắc',3),
	 (1,2,'Quá tuyệt vời không có gì để bàn cãi',5),
	 (2,2,'Quá xịn, giao hàng nhanh, âm thanh chất lượng cao',5),
	 (2,2,'Mang đi chơi quá là hợp lý',5);
INSERT INTO u_order (user_id,address_id,status_id,phone_id,total)
value (
	1,
	1,
	1,
	1,
	300
);
INSERT INTO order_detail (stock_id,order_id,user_id,price,quantity)
value(
	1,
	1,
	1,
	280,
	10
);
INSERT INTO order_detail (stock_id,order_id,user_id,price,quantity)
value(
	1,
	1,
	1,
	280,
	10
);
INSERT INTO boutique.cart (stock_id,user_id,quantity) VALUES
	 (1,3,3),
	 (5,3,1),
	 (7,1,1),
	 (1,1,1),
	 (5,2,4),
	 (1,2,3);