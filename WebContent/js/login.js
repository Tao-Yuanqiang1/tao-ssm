$(function(){
	$('#username').focus().blur(checkName);
	$('#password').blur(checkPassword);
});

function checkName(){
	var name = $('#username').val();
	if(name == null || name == ""){
		//��ʾ����
		$('#count-msg').html("�û�������Ϊ��");
		return false;
	}
	var reg = /^\w{3,10}$/;
	if(!reg.test(name)){
		$('#count-msg').html("����3-10����ĸ�����ֻ��»���");
		return false;
	}
	$('#count-msg').empty();
	return true;
}

function checkPassword(){
	var password = $('#password').val();
	if(password == null || password == ""){
		//��ʾ����
		$('#password-msg').html("���벻��Ϊ��");
		return false;
	}
	var reg = /^\w{3,10}$/;
	if(!reg.test(password)){
		$('#password-msg').html("����3-10����ĸ�����ֻ��»���");
		return false;
	}
	$('#password-msg').empty();
	return true;
}