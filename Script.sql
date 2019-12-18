
create table users(
		user_id serial primary key,
		fname VARCHAR (50) NOT NULL,
		lname varchar(50) not null,
		password VARCHAR (50) UNIQUE NOT null,
		is_employee boolean,
		is_admin boolean		
);

create table accounts(
		account_id serial primary key,
		account_type VARCHAR (50) NOT NULL,
		account_number integer default 100000,
		balance numeric default 0.00,
		interest_rate numeric default 0.02,
		is_joint boolean not null,
		pin_number integer,
		pending_status integer,
		user_id INTEGER NOT NULL REFERENCES users(user_id)
);




drop table if exists accounts;

ALTER TABLE accounts
ADD COLUMN pin_number integer;

create sequence if not exists account_number_sequence
	minvalue 100000
	no maxvalue
	start with 100000
	no cycle
	owned by accounts.account_number;
	
create or replace function set_account_number() returns trigger 
	as 
	$$ 
		begin 
			new.account_number = nextval('account_number_sequence');
			return new;
		end;
	$$ language plpgsql;
	
create trigger trg_account_number
	before insert 
	on accounts
		for each row 
			execute procedure set_account_number();
		
create table transactions(
		id serial primary key,
		account_type varchar(50),
		account_id integer not null REFERENCES accounts(account_id),
		action varchar(50),
		new_balance numeric
	);

	
TRUNCATE TABLE users cascade;
	
TRUNCATE TABLE accounts cascade;

drop table users;
drop table accounts;

create or replace function record_transaction() returns trigger 
as $changes$
begin
	if new.balance <> old.balance then
	insert into transactions values(id, account_type, account_id, action, new_balance);
	end if;
		return new;
end;

$changes$ language plpgsql;

create trigger log_change
before update
on accounts
for each row
execute procedure record_transaction()



			
		