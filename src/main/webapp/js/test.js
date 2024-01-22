window.addEventListener('load', () => {
    const selects = document.querySelectorAll("#select")
    selects[1].addEventListener("click", () => {
        if (!sessionStorage.getItem("isLoad")) return false
        location.assign("http://localhost:8080/TTFweb_war_exploded/jsp/pictures.jsp");
    })
    selects[2].addEventListener("click", () => {
        if (!sessionStorage.getItem("isLoad")) return false
        location.assign("http://localhost:8080/TTFweb_war_exploded/jsp/correct.jsp");
    })
    // music_title.addEventListener("click", () => document.querySelector(".all-items").style.display = "none")
    // loop picture
    let gImgList = document.querySelector("#img-group");
    let clonefirstImg = gImgList.firstElementChild.cloneNode();
    gImgList.appendChild(clonefirstImg);
    let index = 0
    function handleRightBtn() {
        index++;
        gImgList.style.left = -index * 650 + "px";
        // 让添加的图片具有过度效果
        gImgList.style.transition = "0.5s ease";
        if (index === (gImgList.childNodes.length / 2) - 1) {
            index = 0;
            setTimeout(() => {
                gImgList.style.left = 0;
                // 取消过渡 500毫秒之后切换第一张
                gImgList.style.transition = "none";
            }, 500);
        }
    }
    let autoplay = setInterval(handleRightBtn, 2000);
    const gImg = document.getElementById("wrap");
    // 移入停止轮播
    gImg.addEventListener("mouseenter", () => {
        clearInterval(autoplay);
    });
    // 移出继续轮播
    gImg.addEventListener("mouseleave", () => {
        // 设表先关
        clearInterval(autoplay);
        autoplay = setInterval(handleRightBtn, 2000);
    });

    //top - head - img existentialist
    let flat = true;
    const head_img = document.querySelector(".user-priture")
    const content_txt = document.querySelector(".user-content")
    const head_content = document.querySelector(".user-content")
    function toggle() { // 调用此方法，控制遮罩层
        head_content.classList.toggle("user-content-change")
        head_img.classList.toggle("user-priture-change")
    }
    head_img.addEventListener("mouseenter", () => { // 鼠标划入头像
        toggle()
        flat = true // 标志鼠标划入
    })
    head_img.addEventListener("mouseleave", () => { // 鼠标划出头像
        toggle()
        setTimeout(() => flat = false, 1); // 定时将标志改变
    })
    content_txt.addEventListener("mouseenter", () => { // 鼠标划入弹入框
        if (flat === true) {
            setTimeout(() => flat = true, 1); // 利用延时使此方法可以触发
            toggle()
        }
    })
    content_txt.addEventListener("mouseleave", () => {
        if (flat === true) {
            toggle()
            flat = false
        }
    })

    //listen load construct 
    const cover = document.getElementById("pageShadow")
    const load = document.getElementById("logging-block")
    window.openShadow = function () {
        if(flat === true){
            cover.style.display = "block"
            load.style.display = "block"
            document.body.style.overflow = "hidden"
        }
    }
    window.closeShadow = function () {
        cover.style.display = "none"
        load.style.display = "none"
        document.body.style.overflow = "auto"
    }

    //register account and password
    const valueOfAccount = document.getElementById("user-account-value")
    const valueOfPassword = document.getElementById("user-password-value")
    const UserMsg = document.getElementById("user-msg")
    const account = document.getElementById("user-account")
    const password = document.getElementById("user-password")
    window.register = function () {
        account.classList.remove("user-account-extre")
        password.classList.remove("user-password-extre")
        if (valueOfAccount.value === 'root' || new RegExp(/^[a-zA-Z0-9]{5,10}$/).test(valueOfAccount.value)) {
            if (valueOfPassword.value === 123456 || new RegExp(/^\d{9}$/).test(valueOfPassword.value)) {
                let Repassword = prompt("确定密码:")
                if(Repassword === valueOfPassword.value){
                    const registerReq = new XMLHttpRequest()
                    const url = `http://localhost:8080/TTFweb_war_exploded/Login?allowPublicKeyRetrieval=true&useSSL=false&account=${valueOfAccount.value}&password=${valueOfPassword.value}`
                    registerReq.open("POST",url,false)
                    registerReq.send()
                    if(registerReq.status === 400){
                        setTimeout(() => closeShadow(), 500);
                        alert("注册成功!!!")
                        UserMsg.submit()
                    }else if(registerReq.status === 200){
                        alert("账号已存在!!")
                    }
                }else {
                    alert("密码不一致!!!!")
                }
            } else {
                password.classList.add("user-password-extre")
            }
        } else {
            account.classList.add("user-account-extre")
        }
    }
    // assert account and password
    window.logging = function (){
        const loggingReq = new XMLHttpRequest()
        const url = `http://localhost:8080/TTFweb_war_exploded/Login?allowPublicKeyRetrieval=true&useSSL=false&account=${valueOfAccount.value}&password=${valueOfPassword.value}`
        loggingReq.open("POST",url,false)
        loggingReq.send()
        if(loggingReq.status === 200){
            alert("Success")
            sessionStorage.setItem("isLoad","true")
            location.assign("http://localhost:8080/TTFweb_war_exploded/");
        }else if(loggingReq.status === 400){
            alert("账号不存在")
        }
    }

    //Pagination function
    let nodeListOf = [{
        pictureUrl: "resource/war-1-0.webp",
        testContent: "南京大屠杀(Nanjing Massacre)指1931至1945年中国抗日战争期间,中华民国在南京保卫战中失利、首都南京于1937年12月13日沦陷后,在华中派遣军司令松井石根和第6师团长谷寿夫指挥下,侵华日军于南京及附近地区进行长达6周的有组织、有计划、有预谋的大屠杀和奸淫、放火、抢劫等血腥暴行.任何中国人不会忘记的时刻!在南京大屠杀中,大量平民及战俘被日军杀害,无数家庭支离破碎,南京大屠杀的遇难人数超过30万."
    },{
        pictureUrl: "resource/war-1-1.webp",
        testContent: "倒下的士兵(by Robert Capa 1936)西班牙内战时期,一位共和军战士被敌军子弹击中即将倒地的瞬间.拍摄者离得太近了,近到令人难以置信.看照片的人能真切地感受到一个生命正在渐渐逝去,残酷的战争真实地呈现在眼前.这幅照片的拍摄者、马格南图片社创始人之一罗伯特·卡帕曾说过：“如果你拍得不够好,是因为你离得不够近."
    },{
        pictureUrl: "resource/war-1-2.jpg",
        testContent: "战争的恐惧(The Terror of Warby Nick Ut,1972)这张照片摄于1972年6月越战期间,照片中心位置是个裸身的小女孩.她的家被南越军队的飞机误投的燃烧弹炸毁了,她惊恐地撕掉身上着火的衣服,无助地哭喊着和其他孩子一起奔跑.当时她只有9岁,长大后她的身上一直留有烧伤的疤痕."
    }, {
        pictureUrl:"resource/war-1-3.webp",
        testContent: "美军士兵在硫磺岛竖起国旗(Flag Raising on Iwo Jimaby Joe Rosenthal, 1945)这张照片是1945年2月23日(为时36天的硫磺岛战役的第5天)拍下的,反映了美军陆战队士兵攻占硫磺岛主峰后将国旗插上山顶的情景."
    },{
        pictureUrl:"resource/war-1-4.webp",
        testContent:"胜利之吻(V-J Day in Times Squareby Alfred Eisenstaedt, 1945)1945年日本宣布无条件投降,纽约民众纷纷走上街头庆祝胜利.一位水兵在时代广场的欢庆活动中亲吻了身旁的一位女护士,这一瞬间被Life杂志的摄影师抓拍下来,成为传世的经典历史画面."
    }];
    const size = 4
    let page = Math.ceil(nodeListOf.length / size)
    let current = 1;
    const _content = document.querySelector("#wars");
    const showContent = () => {
        // 每次遍历新内容 首先清空
        _content.innerHTML = "";
        nodeListOf.forEach((item, index) => {
            // 遍历计算方法 当前为第1页 一页4个 第一页的数据就是 0 - 3(不包含) 第二页为 4 - 7(不包含) 以此类推
            if (index >= (current - 1) * size && index < current * size) {
                // 每遍历一个创建一个li元素
                const li = document.createElement("div");
                // li元素添加内容
                li.innerHTML = `<div class="war-puture-1" style="position: relative;"><img src="${item.pictureUrl}" alt="the war"
                     style=" position: relative; top:100px; left: 20px; margin-bottom: 40px;"><p class="war-puture-1-p">${item.testContent}</p></div>`
                // 添加到列表元素中
                _content.appendChild(li);
            }
        });
    };
    const footer = document.querySelector(".footer-nav");
    const create = () => {
        footer.innerHTML = ""
        let now = 0;
        page = Math.ceil(nodeListOf.length / size)
        while(now !== page){
            const li = document.createElement("li");
            li.innerText = `${now + 1}`
            li.addEventListener("click",()=>{
                current = event.srcElement.innerText
                showContent()
            })
            footer.appendChild(li)
            now++
        }
        console.log(footer.childNodes)
    }

    const getWar = () => {
        create()
        const GettingReq = new XMLHttpRequest()
        const url = "http://localhost:8080/TTFweb_war_exploded/GetWars?allowPublicKeyRetrieval=true&useSSL=false"
        GettingReq.open("GET",url,false)
        GettingReq.send()
        let resp = eval(GettingReq.response)
        nodeListOf = nodeListOf.concat(resp)
        console.log(nodeListOf)
        create()
    }
    getWar()
})