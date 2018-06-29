#删除县辖数据
delete from region where id in(1102,1202,3102,5002);

#更新市辖为市
update region set name=replace(name,'辖','') where id in(1101,1201,3101,5001);

#更新北京县辖数据
update region set id=110128,parent_id=1101,path='11/1101/110128' where id=110228;
update region set id=110129,parent_id=1101,path='11/1101/110129' where id=110229;

#更新天津县辖数据
update region set id=120125,parent_id=1201,path='12/1201/120125' where id=120225;

#更新上海县辖数据
update region set id=310130,parent_id=3101,path='31/3101/310130' where id=310230;

#更新重庆县辖数据
update region set id=500128,parent_id=5001,path='50/5001/500128' where id=500228;
update region set id=500129,parent_id=5001,path='50/5001/500129' where id=500229;
update region set id=500130,parent_id=5001,path='50/5001/500130' where id=500230;
update region set id=500131,parent_id=5001,path='50/5001/500131' where id=500231;
update region set id=500132,parent_id=5001,path='50/5001/500132' where id=500232;
update region set id=500133,parent_id=5001,path='50/5001/500133' where id=500233;
update region set id=500134,parent_id=5001,path='50/5001/500134' where id=500234;
update region set id=500135,parent_id=5001,path='50/5001/500135' where id=500235;
update region set id=500136,parent_id=5001,path='50/5001/500136' where id=500236;
update region set id=500137,parent_id=5001,path='50/5001/500137' where id=500237;
update region set id=500138,parent_id=5001,path='50/5001/500138' where id=500238;
update region set id=500140,parent_id=5001,path='50/5001/500140' where id=500240;
update region set id=500141,parent_id=5001,path='50/5001/500141' where id=500241;
update region set id=500142,parent_id=5001,path='50/5001/500142' where id=500242;
update region set id=500143,parent_id=5001,path='50/5001/500143' where id=500243;
