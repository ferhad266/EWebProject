var globBtnId = '';
var globWorkerId = 0;
var globPaymentId = 0;
var globRoomId = 0;
var globRegisterId = 0;

$(function () {

    $('body').layout({applyDemoStyles: true});

    $('.ui-layout-center, .ui-layout-east, .ui-layout-west, .ui-layout-south, .ui-layout-north').css('background-color', '#91b6ff');

    $('#newWorkerDialogId').dialog({
        title: 'NEW Worker',
        height: 400,
        width: 400,
        autoOpen: false,
        modal: true,
        buttons: {
            "Save": function () {
                addWorker();
                $(this).dialog('close');
            },
            "Close": function () {
                $(this).dialog('close');
            }
        }
    });

    $('#editWorkerDialogId').dialog({
        title: 'Edit Worker',
        height: 400,
        width: 400,
        autoOpen: false,
        modal: true,
        buttons: {
            "Update": function () {
                updateWorker();
                $(this).dialog('close');
            },
            "Close": function () {
                $(this).dialog('close');
            }
        }
    });

    $('#newRoomDialogId').dialog({
        title: 'NEW Room',
        height: 400,
        width: 400,
        autoOpen: false,
        modal: true,
        buttons: {
            "Save": function () {
                addRoom();
                $(this).dialog('open');
            },
            "Close": function () {
                $(this).dialog('close');
            }
        }
    });

    $('#editRoomDialogId').dialog({
        title: 'Edit Room',
        height: 400,
        width: 400,
        autoOpen: false,
        modal: true,
        buttons: {
            "Update": function () {
                updateRoom();
                $(this).dialog('open');
            },
            "Close": function () {
                $(this).dialog('close');
            }
        }
    });

    $('#newPaymentDialogId').dialog({
        title: 'NEW Payment',
        height: 400,
        width: 400,
        autoOpen: false,
        modal: true,
        buttons: {
            "Save": function () {
                addPayment();
                $(this).dialog('open');
            },
            "Close": function () {
                $(this).dialog('close');
            }
        }
    });

    $('#editPaymentDialogId').dialog({
        title: 'Edit Payment',
        height: 400,
        width: 400,
        autoOpen: false,
        modal: true,
        buttons: {
            "Update": function () {
                updatePayment();
                $(this).dialog('open');
            },
            "Close": function () {
                $(this).dialog('close');
            }
        }
    });

    $('#newRegisterDialogId').dialog({
        title: 'NEW Register',
        height: 400,
        width: 400,
        autoOpen: false,
        modal: true,
        buttons: {
            "Save": function () {
                addRegister();
                $(this).dialog('open');
            },
            "Close": function () {
                $(this).dialog('close');
            }
        }
    });

    $('#editRegisterDialogId').dialog({
        title: 'Edit Register',
        height: 400,
        width: 400,
        autoOpen: false,
        modal: true,
        buttons: {
            "Update": function () {
                updateRegister();
                $(this).dialog('open');
            },
            "Close": function () {
                $(this).dialog('close');
            }
        }
    });

    $('#workerDataBtnId').click(function () {
        getWorkerList();
    });

    $('#roomDataBtnId').click(function () {
        getRoomList();
    });

    $('#paymentDataBtnId').click(function () {
        getPaymentList();
    });

    $('#registerDataBtnId').click(function () {
        getRegisterList();
    });

    $('.btnDesign').click(function () {
        globBtnId = $(this).attr('id');
        $('#keywordId').val('');
    });

    $('#newBtnId').click(function () {
        switch (globBtnId) {
            case 'roomDataBtnId':
                $('#newRoomDialogId').load('views/newRoom.jsp', function () {
                    $(this).dialog('open');
                });
                break;
            case 'workerDataBtnId':
                $('#newWorkerDialogId').load('views/newWorker.jsp', function () {
                    $(this).dialog('open');
                });
                break;
            case 'customerDataBtnId':
                alert('ok');
                break;
            case 'registerDataBtnId':
                $('#newRegisterDialogId').load('cs?action=newRegister', function () {  //ComboBox olacaq bu tablede, bu dialog serverden gelcek
                    $(this).dialog('open');
                });
                break;
            case 'paymentDataBtnId':
                $('#newPaymentDialogId').load('cs?action=newPayment', function () {  //ComboBox olacaq bu tablede, bu dialog serverden gelcek
                    $(this).dialog('open');
                });
                break;
            default:
                alert('Please , select menu!');
        }
    });

    $('#searchBtnId').click(function () {
        searchData();
    });

    $('#keywordId').keyup(function () {
        searchData();
    });

});

function searchData() {
    var keyword = $('#keywordId').val();
    switch (globBtnId) {
        case 'roomDataBtnId':
            searchRoomData(keyword);
            break;
        case 'workerDataBtnId':
            searchWorkerData(keyword);
            break;
        case 'customerDataBtnId':
            alert('ok');
            break;
        case 'registerDataBtnId':
            searchRegisterData(keyword);
            break;
        case 'paymentDataBtnId':
            searchPaymentData(keyword);
            break;
        default:
            alert('Please , select menu!');
    }
}

function getWorkerList() {
    $.ajax({
        url: 'cs?action=getWorkerList',
        type: 'GET',
        dataType: 'html', //xml,json
        success: function (response) {
            $('.ui-layout-center').html(response);
        },
        error: function () {
            alert('Error');
        }
    });
}

function getRoomList() {
    $.ajax({
        url: 'cs?action=getRoomList',
        type: 'GET',
        dataType: 'html', //xml,json
        success: function (response) {
            $('.ui-layout-center').html(response);
        },
        error: function () {
            alert('Error');
        }
    });
}

function getPaymentList() {
    $.ajax({
        url: 'cs?action=getPaymentList',
        type: 'GET',
        dataType: 'html', //xml,json
        success: function (response) {
            $('.ui-layout-center').html(response);
        },
        error: function () {
            alert('Error');
        }
    });
}

function getRegisterList() {
    $.ajax({
        url: 'cs?action=getRegisterList',
        type: 'GET',
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);
        },
        error: function () {
            alert('Error');
        }
    });
}

function addRoom() {

    var roomNumber = $('#roomNumberId').val();
    var roomSituation = $('#roomSituationId').val();
    var price = $('#priceId').val();
    var humanCount = $('#humanCountId').val();
    var roomType = $('#roomTypeId').val();

    var data = {
        'roomNumber': roomNumber,
        'roomSituation': roomSituation,
        'price': price,
        'humanCount': humanCount,
        'roomType': roomType
    };

    $.ajax({
        url: 'cs?action=addRoom',
        type: 'POST',
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == 'success') {
                alert("Room has been successfully added!");
                getRoomList();
            } else {
                alert("PROBLEM!! Worker has not been successfully added!");
            }
        }
    });
}

function addWorker() {

    var name = $('#nameId').val();
    var surname = $('#surnameId').val();
    var dob = $('#dobId').val();
    var fatherName = $('#fatherNameId').val();
    var phone = $('#phoneId').val();
    var email = $('#emailId').val();

    var data = {
        'name': name,
        'surname': surname,
        'dob': dob,
        'fatherName': fatherName,
        'phone': phone,
        'email': email
    };

    $.ajax({
        url: 'cs?action=addWorker',
        type: 'POST',
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == 'success') {
                alert("Worker has been successfully added!");
                getWorkerList();
            } else {
                alert("PROBLEM!! Worker has not been successfully added!");
            }
        }
    });
}

function addPayment() {

    var workerCmb = $('#workerCmbId').val();
    var roomCmb = $('#roomCmbId').val();
    var registerCmb = $('#registerCmbId').val();
    var amount = $('#amountId').val();
    var payDate = $('#dobId').val();

    var data = {
        'workerCmb': workerCmb,
        'roomCmb': roomCmb,
        'registerCmb': registerCmb,
        'amount': amount,
        'payDate': payDate
    };

    $.ajax({
        url: 'cs?action=addPayment',
        type: 'POST',
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == 'success') {
                alert("Payment has been successfully added!");
                getPaymentList();
            } else {
                alert("PROBLEM!! Payment has not been successfully added!")
            }
        }
    });
}

function addRegister() {

    var name = $('#nameId').val();
    var surname = $('#surnameId').val();
    var dob = $('#dobId').val();
    var fatherName = $('#fatherNameId').val();
    var adultCount = $('#adultCountId').val();
    var childCount = $('#childCountId').val();
    var phone = $('#phoneId').val();
    var email = $('#emailId').val();
    var check_in = $('#checkInId').val();
    var check_out = $('#checkOutId').val();
    var workerCmb = $('#workerCmbId').val();
    var roomCmb = $('#roomCmbId').val();

    var data = {
        name: name,
        surname: surname,
        dob: dob,
        fatherName: fatherName,
        adultCount: adultCount,
        childCount: childCount,
        phone: phone,
        email: email,
        check_in: check_in,
        check_out: check_out,
        workerCmb: workerCmb,
        roomCmb: roomCmb
    };

    $.ajax({
        url: 'cs?action=addRegister',
        type: 'POST',
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == 'success') {
                alert("Register has been successfully added!");
                getRegisterList();
            } else {
                alert("PROBLEM! Register has not been successfully added!");
            }
        }
    });

}

function editWorker(workerId) {

    globWorkerId = workerId;

    var data = {
        "workerId": workerId
    };

    $.ajax({
        url: 'cs?action=editWorker',
        type: 'GET',
        dataType: 'html',
        data: data,
        success: function (response) {
            $('#editWorkerDialogId').html(response);
            $('#editWorkerDialogId').dialog('open');
        }
    });
}

function editPayment(paymentId) {

    globPaymentId = paymentId;

    var data = {
        "paymentId": paymentId
    };

    $.ajax({
        url: 'cs?action=editPayment',
        type: 'GET',
        dataType: 'html',
        data: data,
        success: function (response) {
            $('#editPaymentDialogId').html(response);
            $('#editPaymentDialogId').dialog('open');
        }
    });

}

function editRegister(registerId) {

    globRegisterId = registerId;

    var data = {
        "registerId": registerId
    };

    $.ajax({
        url: 'cs?action=editRegister',
        type: 'GET',
        dataType: 'html',
        data: data,
        success: function (response) {
            $('#editRegisterDialogId').html(response);
            $('#editRegisterDialogId').dialog('open');
        }
    });

}

function editRoom(roomId) {

    globRoomId = roomId;
    var data = {
        'roomId': roomId
    };

    $.ajax({
        url: 'cs?action=editRoom',
        type: 'GET',
        dataType: 'html',
        data: data,
        success: function (response) {
            $('#editRoomDialogId').html(response);
            $('#editRoomDialogId').dialog('open');
        }
    });

}

function updateWorker() {

    var name = $('#nameIdU').val();
    var surname = $('#surnameIdU').val();
    var dob = $('#dobIdU').val();
    var fatherName = $('#fatherNameIdU').val();
    var phone = $('#phoneIdU').val();
    var email = $('#emailIdU').val();

    var data = {
        name: name,
        surname: surname,
        dob: dob,
        fatherName: fatherName,
        phone: phone,
        email: email,
        workerId: globWorkerId
    };

    $.ajax({
        url: 'cs?action=updateWorker',
        type: 'POST',
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == 'success') {
                alert("Worker has been successfully updated!");
                getWorkerList();
            } else {
                alert("PROBLEM!! Worker has not been successfully updated!");
            }
        }
    });
}

function updatePayment() {

    var workerCmb = $('#workerCmbIdU').val();
    var registerCmb = $('#registerCmbIdU').val();
    var roomCmb = $('#roomCmbIdU').val();
    var amount = $('#amountIdU').val();
    var payDate = $('#dateIdU').val();

    var data = {
        workerCmb: workerCmb,
        roomCmb: roomCmb,
        registerCmb: registerCmb,
        amount: amount,
        payDate: payDate,
        paymentId: globPaymentId
    };

    $.ajax({
        url: 'cs?action=updatePayment',
        type: 'POST',
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == 'success') {
                alert("Payment has been successfully updated!");
                getPaymentList();
            } else {
                alert("PROBLEM!! Payment not has been successfully updated!");
            }
        }
    });
}

function updateRegister() {

    var name = $('#nameIdU').val();
    var surname = $('#surnameIdU').val();
    var dob = $('#dobIdU').val();
    var fatherName = $('#fatherNameIdU').val();
    var adultCount = $('#adultCountIdU').val();
    var childCount = $('#childCountIdU').val();
    var phone = $('#phoneIdU').val();
    var email = $('#emailIdU').val();
    var check_in = $('#checkInIdU').val();
    var check_out = $('#checkOutIdU').val();
    var workerCmb = $('#workerCmbIdU').val();
    var roomCmb = $('#roomCmbIdU').val();

    var data = {
        name: name,
        surname: surname,
        dob: dob,
        fatherName: fatherName,
        adultCount: adultCount,
        childCount: childCount,
        phone: phone,
        email: email,
        check_in: check_in,
        check_out: check_out,
        workerCmb: workerCmb,
        roomCmb: roomCmb,
        registerId: globRegisterId
    };

    $.ajax({
        url: 'cs?action=updateRegister',
        type: 'POST',
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == 'success') {
                alert("Register has been successfully added!");
                getRegisterList();
            } else {
                alert("PROBLEM!! Register not has been successfully added!");
            }
        }
    });

}

function updateRoom() {

    var roomNumber = $('#roomNumberIdU').val();
    var roomSituation = $('#roomSituationIdU').val();
    var price = $('#priceIdU').val();
    var humanCount = $('#humanCountIdU').val();
    var roomType = $('#roomTypeIdU').val();

    var data = {
        roomNumber: roomNumber,
        roomSituation: roomSituation,
        price: price,
        humanCount: humanCount,
        roomType: roomType,
        roomId: globRoomId
    };

    $.ajax({
        url: 'cs?action=updateRoom',
        type: 'POST',
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == 'success') {
                alert("Room has been successfully updated!");
                getRoomList();
            } else {
                alert("PROBLEM! Room has not been suuccessfully updated!");
            }
        }
    });
}

function deleteWorker(workerId, fullname) {

    var isDelete = confirm("Are you sure to delete " + fullname + "?");
    var data = {
        "workerId": workerId
    };
    if (isDelete) {
        $.ajax({
            url: 'cs?action=deleteWorker',
            type: 'POST',
            data: data,
            dataType: 'text',
            success: function (response) {
                if (response == 'success') {
                    alert("Worker has been successfully deleted!");
                    getWorkerList();
                } else {
                    alert("Problem! Worker has not been deleted!");
                }
            }
        });
    }

}

function deletePayment(paymentId) {

    var isDelete = confirm("Are you sure to delete ?");
    var data = {
        "paymentId": paymentId
    };
    if (isDelete) {
        $.ajax({
            url: 'cs?action=deletePayment',
            type: 'POST',
            data: data,
            dataType: 'text',
            success: function (response) {
                if (response == 'success') {
                    alert("Payment has been successfully deleted!");
                    getPaymentList();
                } else {
                    alert("Problem! Payment has not been deleted!");
                }
            }
        });
    }
}

function deleteRoom(roomId, roomNumber) {

    var isDeleted = confirm("Are you sure to delete" + roomNumber + "?");
    var data = {
        roomId: roomId
    };
    if (isDeleted) {
        $.ajax({
            url: 'cs?action=deleteRoom',
            type: 'POST',
            data: data,
            dataType: 'text',
            success: function (response) {
                if (response == 'success') {
                    alert("Room has been successfully deleted!");
                    getRoomList();
                } else {
                    alert("PROBLEM! Room has not been successfully deleted!");
                }
            }
        });
    }
}

function deleteRegister(registerId, fullname) {

    var isDeleted = confirm("Are you sure to delete" + fullname + "?");
    var data = {
        registerId: registerId
    };
    if (isDeleted) {
        $.ajax({
            url: 'cs?action=deleteRegister',
            type: 'POST',
            data: data,
            dataType: 'text',
            success: function (response) {
                if (response == 'success') {
                    alert("Register has been successfully deleted!");
                    getRegisterList();
                } else {
                    alert("Problem! Register has not been deleted!");
                }
            }
        });
    }

}

function searchWorkerData(keyword) {

    $.ajax({
        url: 'cs?action=searchWorkerData',
        type: 'GET',
        data: 'keyword' + keyword,
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);
        }
    });

}

function searchRoomData(keyword) {

    $.ajax({
        url: 'cs?action=searchRoomData',
        type: 'GET',
        data: 'keyword' + keyword,
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);
        }
    });

}

function advancedSearchPaymentData() {

    var roomCombo = $('#advRoomCmbId').val();
    var registerCombo = $('#advRegisterCmbId').val();
    var advMinAmount = $('#advMinAmountId').val();
    var advMaxAmount = $('#advMaxAmountId').val();
    var advBeginDate = $('#advBeginDateId').val();
    var advEndDate = $('#advEndDateId').val();

    var data = {
        roomCombo: roomCombo,
        registerCombo: registerCombo,
        advMinAmount: advMinAmount,
        advMaxAmount: advMaxAmount,
        advBeginDate: advBeginDate,
        advEndDate: advEndDate
    };

    $.ajax({
        url: 'cs?action=advancedSearchPaymentData',
        type: 'GET',
        data: data,
        dataType: 'html',
        success: function (response) {
            $('#paymentDivId').html(response);
        }
    });

}

function getRegisterListByRoomId(roomId) {

    $.ajax({
        url: 'cs?action=getRegisterListByRoomId',
        type: 'GET',
        data: 'roomId' + roomId,
        dataType: 'html',
        success: function (response) {
            $('#advRegisterCmbId').html(response);
        }
    });
}

