select * from cn_user where cn_user_id = 'be3cdee8f8134cf0ada1314971cfd761';
select * from cn_notebook;
delete from cn_notebook where cn_notebook_name = '笔记本1';
commit;
select * from cn_notebook where cn_user_id = '48595f52-b22c-4485-9244-f4004255b972';

update cn_user set cn_user_password='ICy5YqxZB1uWSwcVLSNLcA==';

commit;

show tables;

delete from cn_user where cn_user_id = '5a20819a-6b55-4479-81e7-f3913c26671a';

cn_notebook_name

select * from cn_notebook where cn_notebook_name = 'action';

select cn_note_body from cn_note where cn_note_id = '8b9b0b03-af5f-407a-b075-a9e433f0c892';

select * from cn_note where cn_notebook_id = '516f6f4f-eaa3-4c76-84ff-530b92c7f64d';

select distinct cn_notebook_id 
		from cn_notebook
		where cn_notebook_name = '笔记本1' and
			  cn_user_id = '39295a3d-cc9b-42b4-b206-a2e7fab7e77c'
			  
select * from cn_share;
desc cn_share;
