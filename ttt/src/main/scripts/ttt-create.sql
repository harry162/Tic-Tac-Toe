create sequence hibernate_sequence minvalue 100;

create table users (
	id integer primary key,
	username varchar(255) unique not null,
	password varchar(255),
	email varchar(255)
);


create table gamewithai(
	
	id integer primary key,
	username_id integer references users(id),
	starttime timestamp,
	endtime timestamp,
	gamefinished boolean,
	savetime timestamp,
	result varchar(5)
);


create table gamewithplayer(
	
	id integer primary key,
	user1st_id integer references users(id),
	user2nd_id integer references users(id),
	starttime timestamp,
	endtime timestamp,
	tie boolean,
	winner_id integer references users(id),
	loser_id integer references users(id)
);


create table savedsteps(
	id integer primary key,
	gameid_id int references GameWithAI(id),
	position varchar(2),
	userpos boolean
);

    create table authorities (
    username    varchar(255) not null references users(username),
    authority   varchar(255) default 'ROLE_USER'
	);


insert into users (id, username, password, email) values(1,'cysun', 'abcd', 'cysun@localhost.localdomain');
insert into users (id, username, password, email) values(2,'hshah', 'abcd', 'hshah9@calstatela.edu');
    insert into authorities values('cysun', 'ROLE_USER');
    insert into authorities values('hshah', 'ROLE_USER');


	CREATE FUNCTION insertdata() returns trigger as $testref$
    BEGIN
    IF (TG_OP='INSERT') THEN
      INSERT INTO authorities (username) values (NEW.username);
      return NEW;
    END IF;
    END;
    $testref$ LANGUAGE plpgsql;
    CREATE TRIGGER testref AFTER INSERT ON users
  	FOR EACH ROW 
  	EXECUTE PROCEDURE insertdata();




select * from gamewithai;
select * from gamewithplayer;
select * from savedsteps;
select * from users;