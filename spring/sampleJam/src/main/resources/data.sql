insert into juser(id, pw, name, main) values('abc', '123', 'hj', 'oooyat@naver.com');
insert into juser(id, pw, name, main) values('ing', '321', 'jh', 'opoyat@gmail.com');
insert into juser(id, pw, name, main) values('test', '0909', 'lee', 'dog@yahoo.co.kr');

insert into jdoc(docId, title, subtitle, ytb, context1, context2, rcmd, rprt, userId, mdfyDate) values(1, '하여가', '음악대장님', 'nkWpCMIwyDc', '음악대장에대해', '갤러리주소', 10, 0, 'ing', '2016/06/01');
insert into jdoc(docId, title, subtitle, ytb, context1, context2, rcmd, rprt, userId, mdfyDate) values(2, 'bang', '황치열', 'EJC_E0qZ8dI', '황치열1등', '갤러리주소', 3, 1, 'abc', '2016/05/31');
insert into jdoc(docId, title, subtitle, ytb, context1, context2, rcmd, rprt, userId, mdfyDate) values(3, '웃긴언니', '제나마블스', 'FYfZwDorJsw', '제나마블스잼서용', '관련주소', 4, 2, 'abc', '2016/06/01');

insert into jsaveDoc(userId, docId) values('abc', 1);
insert into jsaveDoc(userId, docId) values('ing', 2);
insert into jsaveDoc(userId, docId) values('ing', 3);

insert into jkeyword(ko, en, jtype, score) values('음악', 'music', 'joy', 0.810717);
insert into jkeyword(ko, en, jtype, score) values('제나마블스', 'JennaMarbles', 'joy', 0.316916);
insert into jkeyword(ko, en, jtype, score) values('일상', 'Daily routine', 'disgust', 0.267831);
insert into jkeyword(ko, en, jtype, score) values('하현우', 'Hahyeonu', 'joy', 0.316102);
insert into jkeyword(ko, en, jtype, score) values('하여가', 'Did, a very,', 'joy', 0.617134);
insert into jkeyword(ko, en, jtype, score) values('빅뱅', 'bigbang', 'joy', 0.313162);
insert into jkeyword(ko, en, jtype, score) values('경연', 'Contest', 'joy', 0.356306);

insert into jDocRelation(docId, keywordKo) values(1, '음악');
insert into jDocRelation(docId, keywordKo) values(1, '하현우');
insert into jDocRelation(docId, keywordKo) values(1, '하여가');
insert into jDocRelation(docId, keywordKo) values(1, '경연');
insert into jDocRelation(docId, keywordKo) values(2, '음악');
insert into jDocRelation(docId, keywordKo) values(2, '빅뱅');
insert into jDocRelation(docId, keywordKo) values(2, '경연');
insert into jDocRelation(docId, keywordKo) values(3, '제나마블스');
insert into jDocRelation(docId, keywordKo) values(3, '일상');