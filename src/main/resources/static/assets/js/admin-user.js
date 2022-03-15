let indexUser = 0;


function getUser() {
    $.ajax({
        type: "GET",
        //tên API
        url: `http://localhost:8080/admin/users`,
        //xử lý khi thành công
        success: function (data) {
            // hien thi danh sach o day
            let content = '<tr>\n' +
                '<th>Full name</th>\n' +
                '<th>Birth</th>\n' +
                '<th>Email</th>\n' +
                '<th>Username</th>\n' +
                '<th>Password</th>\n' +
                '<th>RePassword</th>\n' +
                '<th>Phone Number</th>\n' +
                '<th>Address</th>\n' +
                '<th>Identify</th>\n' +
                '<th>Role</th>\n' +
                '<th colspan="2">Action</th>\n' +
                '</tr>';
            for (let i = 0; i < data.length; i++) {
                content += displayUser(data[i]);
            }
            document.getElementById("userList").innerHTML = content;
            document.getElementById("formUser").hidden = true;
        }
    });
}

function displayUser(user) {
    return `<tr><td>${user.fullName}</td>
            <td>${user.birth}</td>
            <td>${user.email}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.rePassword}</td>
            <td>${user.phoneNumber}</td>
            <td>${user.address}</td>
            <td>${user.identify}</td>
            <td>${user.role.name}</td>
            <td><button class="btn btn-danger" onclick="deleteUser(${user.id})">Delete</button></td>
            <td><button class="btn btn-warning" onclick="editUser(${user.id})">Edit</button></td></tr>`;
}

function displayFormCreateUser() {
    document.getElementById("formUser").reset()
    document.getElementById("formUser").hidden = false;
    document.getElementById("form-button-user").onclick = function () {
        addNewUser();
    }
    getRole();

}
function getRole() {
    $.ajax({
        type: "GET",
        //tên API
        url: `http://localhost:8080/admin/roles`,
        //xử lý khi thành công
        success: function (data) {
            let content = '<select id="role">\n'
            for (let i = 0; i < data.length; i++) {
                content += displayRole(data[i]);
            }
            content += '</select>'
            document.getElementById('div-role').innerHTML = content;
        }
    });
}

function displayRole(role) {
    return `<option id="${role.id}" value="${role.id}">${role.name}</option>`;
}

function addNewUser() {
    //lay du lieu
    let data = new FormData();
    let fullName = $('#userFullName').val();
    let email = $('#userEmail').val();
    let username = $('#accountName').val();
    let rePassword = $('#rePassword').val();
    let address = $('#address').val();
    let phoneNumber = $('#phoneNumber').val();
    let password = $('#password').val();
    let identify = $('#identify').val();
    let role = $('#role').val();
    let birth = $('#birth').val();
    let newUser = {
        fullName: fullName,
        email: email,
        username: username,
        password: password,
        rePassword: rePassword,
        phoneNumber: phoneNumber,
        birth: birth,
        address: address,
        identify: identify,
        role: {
            id: role,
        },
    };
    data.append("json", new Blob([JSON.stringify(newUser)],{
        type: "application/json"
    }))
    // goi ajax
    $.ajax({
        type: "POST",
        data: data,
        processData: false,
        contentType: false,
        //tên API
        url: "http://localhost:8080/admin/users",
        //xử lý khi thành công
        success: function () {
            getUser();
        }

    });
    //chặn sự kiện mặc định của thẻ
    event.preventDefault();
}

function editUser(id) {
    $.ajax({
        type: "GET",
        //tên API
        url: `http://localhost:8080/admin/users/${id}`,
        //xử lý khi thành công
        success: function (data) {
            $('#name').val(data.name);
            $('#price').val(data.price);
            $('#image').val(data.image);
            $('#description').val(data.description);
            index = data.id;
            document.getElementById("form").hidden = false;
            document.getElementById("form-button").onclick = function () {
                editProduct1()
            };
            getCategory();
        }
    });
}

function editProduct1() {
    //lay du lieu
    let name = $('#name').val();
    let price = $('#price').val();
    let image = $('#image').val();
    let description = $('#description').val();
    let category = $('#category').val();
    let newProduct = {
        name: name,
        price: price,
        image: image,
        description: description,
        category: {
            id: category,
        }
    };
    // goi ajax
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        data: JSON.stringify(newProduct),
        //tên API
        url: `http://localhost:8080/api/products/${index}`,
        //xử lý khi thành công
        success: function () {
            getProduct()
        }
    });
    //chặn sự kiện mặc định của thẻ
    event.preventDefault();
}



function getProductByPage(page) {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/api/products/page?page=${page}`,
        success: function (data) {
            let array = data.content
            let content = '<tr>\n' +
                '<th>ProductName</th>\n' +
                '<th>Price</th>\n' +
                '<th>Image</th>\n' +
                '<th>Desciption</th>\n' +
                '<th>Category</th>\n' +
                '<th colspan="2">Action</th>\n' +
                '</tr>';
            for (let i = 0; i < array.length; i++) {
                content += displayProduct(array[i]);
            }
            document.getElementById("productList").innerHTML = content;
            document.getElementById("displayPage").innerHTML = displayPage(data)
            document.getElementById("form").hidden = true;
            if (data.pageable.pageNumber === 0) {
                document.getElementById("backup").hidden = true
            }
            if (data.pageable.pageNumber + 1 === data.totalPages) {
                document.getElementById("next").hidden = true
            }
        }
    });
}

function displayPage(data){
    return `<button class="btn btn-primary" id="backup" onclick="isPrevious(${data.pageable.pageNumber})">Previous</button>
    <span>${data.pageable.pageNumber+1} | ${data.totalPages}</span>
    <button class="btn btn-primary" id="next" onclick="isNext(${data.pageable.pageNumber})">Next</button>`
}

function isPrevious(pageNumber) {
    getProductByPage(pageNumber-1)
}

function isNext(pageNumber) {
    getProductByPage(pageNumber+1)
}

function deleteProduct(id) {
    $.ajax({
        type: "DELETE",
        url: `http://localhost:8080/api/products/${id}`,
        success: function () {
            getProduct()
        }
    });
}

function searchProduct() {
    let search = document.getElementById("search").value;
    $.ajax({
        type: "GET",
        //tên API
        url: `http://localhost:8080/api/products/search?search=${search}`,
        //xử lý khi thành công
        success: function (data) {
            // hien thi danh sach o day
            let content = '<tr>\n' +
                '<th>ProductName</th>\n' +
                '<th>Price</th>\n' +
                '<th>Image</th>\n' +
                '<th>Desciption</th>\n' +
                '<th colspan="2">Action</th>\n' +
                '</tr>';
            for (let i = 0; i < data.length; i++) {
                content += displayProduct(data[i]);
            }
            document.getElementById('productList').innerHTML = content;
            document.getElementById("searchForm").reset()
        }
    });
    event.preventDefault();
}

function displayManagerUser(){
    document.getElementById("manager-teacher").hidden=true;
    document.getElementById("manager-user").hidden=false;
    getUser();
}



