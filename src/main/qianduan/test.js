//联系人数据
let contacts = [];
let table = document.getElementById("contactTable");
console.log(table == null)

// 初始化联系人列表
function initContactList() {
    //请求数据：
    axios.get("http://localhost:9090/contact/allContacts")
        .then(response => {
            contacts = response.data.data;
            showContacts();
        }).catch(error => {
        console.error(error)
    })
}

function showContacts() {
    // 清空表格内容
    while (table.rows.length > 1) {
        table.deleteRow(1);
    }
    // 添加联系人数据到表格
    for (let i = 0; i < contacts.length; i++) {
        const row = table.insertRow(i + 1);
        const cell1 = row.insertCell(0);
        const cell2 = row.insertCell(1);
        const cell3 = row.insertCell(2);
        const cell4 = row.insertCell(3);
        cell1.innerHTML = contacts[i].cname;
        cell2.innerHTML = contacts[i].caddress;
        cell3.innerHTML = contacts[i].cphone;
        const deleteButtonHtml = `<button class="delete" onclick="deleteContact('${contacts[i].cname}')">删除</button>`;

        const editButtonHtml = `<button class="edit" onclick="editContact('${i + 1}')">编辑</button>`;
        cell4.innerHTML = deleteButtonHtml + " " + editButtonHtml;
    }
}

// 新增联系人
document.getElementById("addContactForm").addEventListener("submit", function (event) {
    event.preventDefault();
    let name = document.getElementById("name").value;
    let address = document.getElementById("address").value;
    let phone = document.getElementById("phone").value;
    const contact = {
        cname: name,
        caddress: address,
        cphone: phone
    }
    contacts.push(contact)
    showContacts();
    axios.post('http://localhost:9090/contact/addContact', contact)
        .then(response => {
            alert("添加成功！")
        }).catch(error => {
        console.error(error)
    })
    // 清空输入框
    document.getElementById("name").value = "";
    document.getElementById("address").value = "";
    document.getElementById("phone").value = "";
    // // 重新渲染联系人列表
    // initContactList();
});

// 删除联系人
function deleteContact(cname) {
    // 弹出确认窗口
    if (confirm('确定要删除此联系人吗？')) {
        // 用户点击了确认按钮，执行删除操作
        //请求删除数据请求：
        axios.delete(`http://localhost:9090/contact/deleteContact/${cname}`)
            .then(response => {
                alert("删除成功！");
                initContactList();
            }).catch(error => {
            console.error(error)
        })
    }
}

// 编辑联系人
function editContact(index) {
    let listIndex = index - 1;
    // 将姓名、地址、电话号码单元格变成可编辑状态
    let nameCell = table.rows[index].cells[0];
    let addressCell = table.rows[index].cells[1];
    let phoneCell = table.rows[index].cells[2];
    let actionCell = table.rows[index].cells[3];
    nameCell.innerHTML = `<input type="nameText" value="${contacts[listIndex].cname}">`;
    addressCell.innerHTML = `<input type="addressText" value="${contacts[listIndex].caddress}">`;
    phoneCell.innerHTML = `<input type="phoneText" value="${contacts[listIndex].cphone}">`;
    //其余的单元格不能变化
    for (let i = 0; i < contacts.length; ++i) {
        if (i == listIndex) {
            continue;
        }
        let ele = table.rows[i+1].cells[3];
        ele.innerHTML = `
        <button class="delete" disabled=true onClick="deleteContact('${contacts[listIndex].cname}')">删除</button>
        <button class="edit" disabled=true onClick="editContact(${index})">编辑</button>`;
    }
    // 将操作栏的删除和编辑按钮变成确定和取消按钮
    actionCell.innerHTML = `
      <button class="save" onclick="saveContact(${index})">确定</button>
      <button class="cancel" onclick="cancelEdit(${index})">取消</button>
    `;
}

// 保存联系人信息
function saveContact(index) {
    let listIndex = index - 1;
    let nameCell = table.rows[index].cells[0];
    let addressCell = table.rows[index].cells[1];
    let phoneCell = table.rows[index].cells[2];
    // 获取最新的姓名、地址、电话号码
    const newName = nameCell.querySelector("input").value;
    const newAddress = addressCell.querySelector("input").value;
    const newPhone = phoneCell.querySelector("input").value;
    // 更新联系人信息
    axios.post(`http://localhost:9090/contact/updateContact`, {
        cnumber: contacts[listIndex].cnumber,
        cname: newName,
        caddress: newAddress,
        cphone: newPhone
    }).then(response => {
        alert("联系人信息已更新！");
        // 更新成功，将联系人信息更新到前端存储的数据中
        contacts[listIndex].cname = newName;
        contacts[listIndex].caddress = newAddress;
        contacts[listIndex].cphone = newPhone;
        // 重新渲染联系人列表
        initContactList();
    }).catch(error => {
        console.error(error);
        alert("联系人信息更新失败！");
        // 更新失败，回滚修改
        initContactList();
    });
}

// 取消编辑联系人
function cancelEdit(index) {
    let listIndex = index - 1;
    let nameCell = table.rows[index].cells[0];
    let addressCell = table.rows[index].cells[1];
    let phoneCell = table.rows[index].cells[2];
    // 将姓名、地址、电话号码单元格变回非编辑状态
    nameCell.innerHTML = contacts[listIndex].cname;
    addressCell.innerHTML = contacts[listIndex].caddress;
    phoneCell.innerHTML = contacts[listIndex].cphone;
    // 将操作栏的确定和取消按钮变回删除和编辑按钮
    recoverEdit();
}
function recoverEdit() {
    for (let i = 0; i < contacts.length; ++i) {
        let actionCell = table.rows[i+1].cells[3];
        actionCell.innerHTML = `
        <button class="delete" onclick="deleteContact('${contacts[i].cname}')">删除</button>
        <button class="edit" onclick="editContact(${i+1})">编辑</button>
    `;
    }
}

// 初始化页面
initContactList();