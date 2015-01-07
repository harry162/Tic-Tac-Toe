
    create table gamewithai (
        id int4 not null,
        endtime timestamp,
        gamefinished boolean not null,
        result varchar(255),
        savetime timestamp,
        starttime timestamp,
        username_id int4,
        primary key (id)
    );

    create table gamewithplayer (
        id int4 not null,
        endtime timestamp,
        starttime timestamp,
        tie boolean not null,
        loser_id int4,
        user1st_id int4,
        user2nd_id int4,
        winner_id int4,
        primary key (id)
    );

    create table savedsteps (
        id int4 not null,
        position varchar(255),
        userpos boolean not null,
        gameid_id int4,
        primary key (id)
    );

    create table users (
        id int4 not null,
        email varchar(255),
        password varchar(255),
        username varchar(255),
        primary key (id)
    );

    alter table gamewithai 
        add constraint FK_fu5bqlgadj0vqdd0wx4b9fk2d 
        foreign key (username_id) 
        references users;

    alter table gamewithplayer 
        add constraint FK_nnxqja77sfihtdgio1pongrht 
        foreign key (loser_id) 
        references users;

    alter table gamewithplayer 
        add constraint FK_13j4ldfgk3o77uehvgsowqkyc 
        foreign key (user1st_id) 
        references users;

    alter table gamewithplayer 
        add constraint FK_n9fphcg4ayx9ckktvhc0r8b34 
        foreign key (user2nd_id) 
        references users;

    alter table gamewithplayer 
        add constraint FK_q033c1666q096nt6uhhxnui1 
        foreign key (winner_id) 
        references users;

    alter table savedsteps 
        add constraint FK_62coegx4k0aa22ecqa507j2ev 
        foreign key (gameid_id) 
        references gamewithai;

    create sequence hibernate_sequence;
