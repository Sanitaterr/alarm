let dayTime = 0
let daymonth = 0
// 获取当前时间
function getNowTime() {
	const now = new Date();
	const year = now.getFullYear() + '年';
	const month = ('0' + (now.getMonth() + 1)).slice(-2) + '月';
	const day = ('0' + now.getDate()).slice(-2) + '日';
	const hours = ('0' + now.getHours()).slice(-2) + ':';
	const minutes = ('0' + now.getMinutes()).slice(-2) + ':';
	const seconds = ('0' + now.getSeconds()).slice(-2);
	const formattedTime = year + month + day + hours + minutes + seconds;
	dayTime = ('0' + now.getDate()).slice(-2)//日
	daymonth = ('0' + (now.getMonth() + 1)).slice(-2)//月
	document.querySelector('.time').innerHTML = formattedTime
	setTimeout(getNowTime, 1000);
}
getNowTime()

//获取username
function getQueryParams() {
	myAxios({
		url:baseURL+'/user/getInfo'
	}).then(result => {
		console.log(result.data);
		document.querySelector('.u').innerHTML =`当前用户：${result.data.username}`
		// document.querySelector('.userImg1').src = result.data.photo
		console.log(document.querySelector('.userImg1').src);
	}).catch(error => {
		console.log(error);
	})
}
getQueryParams();

//退出登录
document.querySelector('.exit').addEventListener('click' , () => {
	localStorage.clear()
	location.href = './login.html'
})

//分页循环
function forpage(total) {
	const pageSize = 30
	const totalPage = parseInt((total % pageSize == 0) ? (total / pageSize) : (total / pageSize + 1))
	const pagination = document.querySelector('.pagination')
	pagination.innerHTML = ''
	if(totalPage === 1)
	{
		pagination.innerHTML = pagination.innerHTML+`<li data-page="0"><a href="#" class="le">上一页</a></li>`
		pagination.innerHTML = pagination.innerHTML+`<li data-page="1"><a href="#" class="li2 centeractive">1</a></li>`
		pagination.innerHTML = pagination.innerHTML+`<li data-page="100"><a href="#" class="ri">下一页</a></li>`
		document.querySelector('.center').classList.add('centers')
		document.querySelector('.centers').classList.remove('center')
		page(1)
	}else if(totalPage === 2){
		pagination.innerHTML = pagination.innerHTML+`<li data-page="0"><a href="#" class="le">上一页</a></li>`
		pagination.innerHTML = pagination.innerHTML+`<li data-page="1"><a href="#" class="li2 centeractive">1</a></li>`
		pagination.innerHTML = pagination.innerHTML+`<li data-page="2"><a href="#" class="li2">2</a></li>`
		pagination.innerHTML = pagination.innerHTML+`<li data-page="100"><a href="#" class="ri">下一页</a></li>`
		document.querySelector('.center').classList.add('centerss')
		document.querySelector('.centerss').classList.remove('center')
		page(1)
		document.querySelector('[data-page="0"]').addEventListener('click',() => {
			let thispg = document.querySelector('.centeractive').closest('LI').dataset.page
			if(thispg > 1)
			{
				thispg--
				document.querySelector('.centeractive').classList.remove('centeractive')
			}
			page(thispg)
			document.querySelector(`[data-page="${thispg}"] a`).classList.add('centeractive')
		})
		document.querySelector('[data-page="100"]').addEventListener('click',() => {
			let thispg = document.querySelector('.centeractive').closest('LI').dataset.page
			if(thispg < totalPage)
			{
				thispg++
				document.querySelector('.centeractive').classList.remove('centeractive')
			}
			page(thispg)
			document.querySelector(`[data-page="${thispg}"] a`).classList.add('centeractive')
		})
		document.querySelector('[data-page="1"]').addEventListener('click',() => {
			document.querySelector('.centeractive').classList.remove('centeractive')
			document.querySelector(`[data-page="1"] a`).classList.add('centeractive')
			page(1)
		})
		document.querySelector('[data-page="2"]').addEventListener('click',() => {
			document.querySelector('.centeractive').classList.remove('centeractive')
			document.querySelector(`[data-page="2"] a`).classList.add('centeractive')
			page(2)
		})
	}else if(totalPage === 3){
		pagination.innerHTML = pagination.innerHTML+`<li data-page="0"><a href="#" class="le">上一页</a></li>`
		pagination.innerHTML = pagination.innerHTML+`<li data-page="1"><a href="#" class="li2 centeractive">1</a></li>`
		pagination.innerHTML = pagination.innerHTML+`<li data-page="2"><a href="#" class="li2">2</a></li>`
		pagination.innerHTML = pagination.innerHTML+`<li data-page="3"><a href="#" class="li2">3</a></li>`
		pagination.innerHTML = pagination.innerHTML+`<li data-page="100"><a href="#" class="ri">下一页</a></li>`
		document.querySelector('.center').classList.add('cente')
		document.querySelector('.cente').classList.remove('center')
		page(1)
		document.querySelector('[data-page="0"]').addEventListener('click',() => {
			let thispg = document.querySelector('.centeractive').closest('LI').dataset.page
			if(thispg > 1)
			{
				thispg--
				document.querySelector('.centeractive').classList.remove('centeractive')
			}
			page(thispg)
			document.querySelector(`[data-page="${thispg}"] a`).classList.add('centeractive')
		})
		document.querySelector('[data-page="100"]').addEventListener('click',() => {
			let thispg = document.querySelector('.centeractive').closest('LI').dataset.page
			if(thispg < totalPage)
			{
				thispg++
				document.querySelector('.centeractive').classList.remove('centeractive')
			}
			page(thispg)
			document.querySelector(`[data-page="${thispg}"] a`).classList.add('centeractive')
		})
		document.querySelector('[data-page="1"]').addEventListener('click',() => {
			document.querySelector('.centeractive').classList.remove('centeractive')
			document.querySelector(`[data-page="1"] a`).classList.add('centeractive')
			page(1)
		})
		document.querySelector('[data-page="2"]').addEventListener('click',() => {
			document.querySelector('.centeractive').classList.remove('centeractive')
			document.querySelector(`[data-page="2"] a`).classList.add('centeractive')
			page(2)
		})
		document.querySelector('[data-page="3"]').addEventListener('click',() => {
			document.querySelector('.centeractive').classList.remove('centeractive')
			document.querySelector(`[data-page="3"] a`).classList.add('centeractive')
			page(3)
		})
	}else {
		for(let i = 0;i <= totalPage+1;i++)
		{
				if(i === 0 )
				{
					pagination.innerHTML = pagination.innerHTML+`<li data-page="${i}"><a href="#" class="le">上一页</a></li>`
				}else if(i === (totalPage+1))
				{
					pagination.innerHTML = pagination.innerHTML+`<li data-page="100"><a href="#" class="ri">下一页</a></li>`
				}else if(i === 1)
				{
					pagination.innerHTML = pagination.innerHTML+`<li data-page="${i}"><a href="#" class="li2 centeractive">${i}</a></li>`
				}else  {
					pagination.innerHTML = pagination.innerHTML+`<li data-page="${i}"><a href="#" class="li2">${i}</a></li>`
					if(i>4)
					{
						document.querySelector(`[data-page="${i}"]`).style.display = 'none'
					}
				}
		}
		page(1)
		let thispages = 1
		document.querySelector('[data-page="0"]').addEventListener('click',() => {
				if(thispages > 1)
				{
					thispages--;
				}
				page(thispages)
				if(thispages === 1)
				{
					for(let i = 1 ; i <= totalPage ; i++)
						{
							document.querySelector(`[data-page="${i}"]`).style.display = "none"
						}
						document.querySelector('.centeractive').classList.remove('centeractive')
						document.querySelector(`[data-page="${thispages}"]`).style.display = "block"
						document.querySelector(`[data-page="${thispages}"] a`).classList.add('centeractive')
						document.querySelector(`[data-page="${thispages+1}"]`).style.display = "block"
						document.querySelector(`[data-page="${thispages+2}"]`).style.display = "block"
						document.querySelector(`[data-page="${thispages+3}"]`).style.display = "block"
				}else if(thispages === 2){
					for(let i = 1 ; i <= totalPage ; i++)
					{
						document.querySelector(`[data-page="${i}"]`).style.display = "none"
					}
					document.querySelector('.centeractive').classList.remove('centeractive')
					document.querySelector(`[data-page="${thispages-1}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages}"] a`).classList.add('centeractive')
					document.querySelector(`[data-page="${thispages}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages+1}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages+2}"]`).style.display = "block"
				}else if(thispages === 3){
					for(let i = 1 ; i <= totalPage ; i++)
						{
							document.querySelector(`[data-page="${i}"]`).style.display = "none"
						}
						document.querySelector('.centeractive').classList.remove('centeractive')
						document.querySelector(`[data-page="${thispages-2}"]`).style.display = "block"
						document.querySelector(`[data-page="${thispages}"] a`).classList.add('centeractive')
						document.querySelector(`[data-page="${thispages-1}"]`).style.display = "block"
						document.querySelector(`[data-page="${thispages}"]`).style.display = "block"
						document.querySelector(`[data-page="${thispages+1}"]`).style.display = "block"
				}else {
					for(let i = 1 ; i <= totalPage ; i++)
					{
						document.querySelector(`[data-page="${i}"]`).style.display = "none"
					}
					document.querySelector('.centeractive').classList.remove('centeractive')
					document.querySelector(`[data-page="${thispages-2}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages}"] a`).classList.add('centeractive')
					document.querySelector(`[data-page="${thispages-1}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages+1}"]`).style.display = "block"
				}
		})
		document.querySelector('[data-page="100"]').addEventListener('click',() => {
			if(thispages < totalPage)
				{
					thispages++;
				}
				page(thispages)
				if(thispages === totalPage)
				{
					for(let i = 1 ; i <= totalPage ; i++)
							{
								document.querySelector(`[data-page="${i}"]`).style.display = "none"
							}
							document.querySelector('.centeractive').classList.remove('centeractive')
							document.querySelector(`[data-page="${totalPage-3}"]`).style.display = "block"
							document.querySelector(`[data-page="${totalPage}"] a`).classList.add('centeractive')
							document.querySelector(`[data-page="${totalPage-2}"]`).style.display = "block"
							document.querySelector(`[data-page="${totalPage-1}"]`).style.display = "block"
							document.querySelector(`[data-page="${totalPage}"]`).style.display = "block"
				}else if(thispages === 2){
					for(let i = 1 ; i <= totalPage ; i++)
					{
						document.querySelector(`[data-page="${i}"]`).style.display = "none"
					}
					document.querySelector('.centeractive').classList.remove('centeractive')
					document.querySelector(`[data-page="${thispages-1}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages}"] a`).classList.add('centeractive')
					document.querySelector(`[data-page="${thispages}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages+1}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages+2}"]`).style.display = "block"
				}else if(thispages === 3){
					for(let i = 1 ; i <= totalPage ; i++)
						{
							document.querySelector(`[data-page="${i}"]`).style.display = "none"
						}
						document.querySelector('.centeractive').classList.remove('centeractive')
						document.querySelector(`[data-page="${thispages-2}"]`).style.display = "block"
						document.querySelector(`[data-page="${thispages}"] a`).classList.add('centeractive')
						document.querySelector(`[data-page="${thispages-1}"]`).style.display = "block"
						document.querySelector(`[data-page="${thispages}"]`).style.display = "block"
						document.querySelector(`[data-page="${thispages+1}"]`).style.display = "block"
				}else {
					for(let i = 1 ; i <= totalPage ; i++)
					{
						document.querySelector(`[data-page="${i}"]`).style.display = "none"
					}
					document.querySelector('.centeractive').classList.remove('centeractive')
					document.querySelector(`[data-page="${thispages-2}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages}"] a`).classList.add('centeractive')
					document.querySelector(`[data-page="${thispages-1}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages+1}"]`).style.display = "block"
				}
		})
			for(let k = 1 ; k <= totalPage ; k++)
			{
					if(k === 1)
					{
						document.querySelector(`[data-page="${k}"]`).addEventListener('click' , () => {
							for(let i = 1 ; i <= totalPage ; i++)
							{
								document.querySelector(`[data-page="${i}"]`).style.display = "none"
							}
							document.querySelector('.centeractive').classList.remove('centeractive')
							document.querySelector(`[data-page="${k}"]`).style.display = "block"
							document.querySelector(`[data-page="${k}"] a`).classList.add('centeractive')
							document.querySelector(`[data-page="${k+1}"]`).style.display = "block"
							document.querySelector(`[data-page="${k+2}"]`).style.display = "block"
							document.querySelector(`[data-page="${k+3}"]`).style.display = "block"
							thispages = k
							page(k)
						})
					}else if(k === 2)
					{
						document.querySelector(`[data-page="${k}"]`).addEventListener('click' , () => {
							for(let i = 1 ; i <= totalPage ; i++)
							{
								document.querySelector(`[data-page="${i}"]`).style.display = "none"
							}
							document.querySelector('.centeractive').classList.remove('centeractive')
							document.querySelector(`[data-page="${k-1}"]`).style.display = "block"
							document.querySelector(`[data-page="${k}"] a`).classList.add('centeractive')
							document.querySelector(`[data-page="${k}"]`).style.display = "block"
							document.querySelector(`[data-page="${k+1}"]`).style.display = "block"
							document.querySelector(`[data-page="${k+2}"]`).style.display = "block"
							thispages = k
							page(k)
						})
					}else if(k === 3)
					{
						document.querySelector(`[data-page="${k}"]`).addEventListener('click' , () => {
							for(let i = 1 ; i <= totalPage ; i++)
							{
								document.querySelector(`[data-page="${i}"]`).style.display = "none"
							}
							document.querySelector('.centeractive').classList.remove('centeractive')
							document.querySelector(`[data-page="${k-2}"]`).style.display = "block"
							document.querySelector(`[data-page="${k}"] a`).classList.add('centeractive')
							document.querySelector(`[data-page="${k-1}"]`).style.display = "block"
							document.querySelector(`[data-page="${k}"]`).style.display = "block"
							document.querySelector(`[data-page="${k+1}"]`).style.display = "block"
							thispages = k
							page(k)
						})
					}else if(k === totalPage)
					{
						document.querySelector(`[data-page="${k}"]`).addEventListener('click' , () => {
							for(let i = 1 ; i <= totalPage ; i++)
							{
								document.querySelector(`[data-page="${i}"]`).style.display = "none"
							}
							document.querySelector('.centeractive').classList.remove('centeractive')
							document.querySelector(`[data-page="${totalPage-3}"]`).style.display = "block"
							document.querySelector(`[data-page="${k}"] a`).classList.add('centeractive')
							document.querySelector(`[data-page="${totalPage-2}"]`).style.display = "block"
							document.querySelector(`[data-page="${totalPage-1}"]`).style.display = "block"
							document.querySelector(`[data-page="${totalPage}"]`).style.display = "block"
							thispages = k
							page(k)
						})
					}else
					{
						document.querySelector(`[data-page="${k}"]`).addEventListener('click' , () => {
							for(let i = 1 ; i <= totalPage ; i++)
							{
								document.querySelector(`[data-page="${i}"]`).style.display = "none"
							}
							document.querySelector('.centeractive').classList.remove('centeractive')
							document.querySelector(`[data-page="${k-2}"]`).style.display = "block"
							document.querySelector(`[data-page="${k}"] a`).classList.add('centeractive')
							document.querySelector(`[data-page="${k-1}"]`).style.display = "block"
							document.querySelector(`[data-page="${k}"]`).style.display = "block"
							document.querySelector(`[data-page="${k+1}"]`).style.display = "block"
							thispages = k
							page(k)
						})
					}
			}
	}
}

//点击修改
function update()
{
	document.querySelectorAll('.change').forEach(item => {
		item.addEventListener('click',() => {
			const part = document.querySelector('.pops')
			part.style.display = 'block'
			const it = item.parentNode
			console.log(it.querySelector('#sn1').innerHTML);
			console.log(part.querySelector("#con1").value);
			part.querySelector("#con1").value=it.querySelector('#sn1').innerHTML
			part.querySelector("#con2").value=it.querySelector('#sn2').innerHTML
			part.querySelector("#con3").value=it.querySelector('#sn3').innerHTML
			part.querySelector("#con4").value=it.querySelector('#sn6').innerHTML
			part.querySelector("#con5").value=it.querySelector('#sn5').innerHTML
			part.querySelector("#con6").value=it.querySelector('#sn4').innerHTML
			if(it.querySelector('#sn7').innerHTML !== "null")
			{
				part.querySelector("#con7").value=it.querySelector('#sn7').innerHTML
			}else {
				part.querySelector("#con7").value="暂未确认"
			}
			if(it.querySelector('#sn8').innerHTML !== "null")
			{
				part.querySelector("#con8").value=it.querySelector('#sn8').innerHTML
			}else {
				part.querySelector("#con8").value="暂未恢复"
			}
		})
		document.querySelector('.updateclose').addEventListener('click',() => {
			document.querySelector('.pops').style.display = 'none'
		})
		document.querySelector('.editorbutton').addEventListener('click',() => {
			document.querySelector('.pops').style.display = 'none'
			document.querySelector('.sucess').style.display = 'block'
			setInterval(function() {
				document.querySelector('.sucess').style.display = 'none'
			},1500)
		})
	})
}

//总条数
myAxios({
	url:baseURL+'/alarm/selectAllAlarm',
	method:'POST'
}).then(result => {
	console.log(result.data);
	forpage(result.data.length)
	document.querySelector('.xj').innerHTML=`共${result.data.length}条`
}).catch(error => {
	console.dir(error)
})

//警报展示
function show(datas)
{
	let items = ''
	let alarm = ''
		for(let i = 1; i<=datas.length;i++)
	{
				if(datas[i-1].confirmStatus && datas[i-1].recoverStatus)
				{
					alarm = '历史警报'
				}else {
					alarm = '实时警报'
				}
				if(datas[i-1].confirmStatus)
				{
					datas[i-1].confirmStatus='已确认'
				}else {
					datas[i-1].confirmStatus='未确认'
				}
				if(datas[i-1].recoverStatus)
				{
					datas[i-1].recoverStatus='已恢复'
				}else {
					datas[i-1].recoverStatus='未恢复'
				}
		if(i % 2 === 0)
		{
			if(datas[i-1].type === '电源电压不稳定')
		{
			items = items+

			`<li class="spanp">
			<span class="span1" id="sn1">${alarm}</span>
			<span class="span2" id="sn2">${datas[i-1].source}</span>
			<span class="spans3" id="sn3">${datas[i-1].type}</span>
			<span class="span4" id="sn4">${datas[i-1].occurTime}</span>
			<span class="span6" id="sn5">${datas[i-1].confirmStatus}&ensp;${datas[i-1].recoverStatus}</span>
			<span class="span7" id="sn6">${datas[i-1].level}级警报</span>
			<span class="spandis" id="sn7">${datas[i-1].confirmTime}</span>
			<span class="spandis" id="sn8">${datas[i-1].recoverTime}</span>
			<span class="change">修改</span>
	</li>`
		}else if(datas[i-1].type === '压力异常')
		{
			items = items+

			`<li class="spanp">
			<span class="span1" id="sn1">${alarm}</span>
			<span class="span2" id="sn2">${datas[i-1].source}</span>
			<span class="spanps3" id="sn3">${datas[i-1].type}</span>
			<span class="span4" id="sn4">${datas[i-1].occurTime}</span>
			<span class="span6" id="sn5">${datas[i-1].confirmStatus}&ensp;${datas[i-1].recoverStatus}</span>
			<span class="span7" id="sn6">${datas[i-1].level}级警报</span>
			<span class="spandis" id="sn7">${datas[i-1].confirmTime}</span>
			<span class="spandis" id="sn8">${datas[i-1].recoverTime}</span>
			<span class="change">修改</span>
	</li>`
		}else if(datas[i-1].type === '湿度过高')
		{
			items = items+

			`<li class="spanp">
			<span class="span1" id="sn1">${alarm}</span>
			<span class="span2" id="sn2">${datas[i-1].source}</span>
			<span class="spanps23" id="sn3">${datas[i-1].type}</span>
			<span class="span4" id="sn4">${datas[i-1].occurTime}</span>
			<span class="span6" id="sn5">${datas[i-1].confirmStatus}&ensp;${datas[i-1].recoverStatus}</span>
			<span class="span7" id="sn6">${datas[i-1].level}级警报</span>
			<span class="spandis" id="sn7">${datas[i-1].confirmTime}</span>
			<span class="spandis" id="sn8">${datas[i-1].recoverTime}</span>
			<span class="change">修改</span>
	</li>`
		}else  if(datas[i-1].type === '温度过高')
		{
			items = items+

			`<li class="spanp">
			<span class="span1" id="sn1">${alarm}</span>
			<span class="span2" id="sn2">${datas[i-1].source}</span>
			<span class="spanps33" id="sn3">${datas[i-1].type}</span>
			<span class="span4" id="sn4">${datas[i-1].occurTime}</span>
			<span class="span6" id="sn5">${datas[i-1].confirmStatus}&ensp;${datas[i-1].recoverStatus}</span>
			<span class="span7" id="sn6">${datas[i-1].level}级警报</span>
			<span class="spandis" id="sn7">${datas[i-1].confirmTime}</span>
			<span class="spandis" id="sn8">${datas[i-1].recoverTime}</span>
			<span class="change">修改</span>
	</li>`
		}else if(datas[i-1].type === '传感器失效')
		{
			items = items+

			`<li class="spanp">
			<span class="span1" id="sn1">${alarm}</span>
			<span class="span2" id="sn2">${datas[i-1].source}</span>
			<span class="spanps43" id="sn3">${datas[i-1].type}</span>
			<span class="span4" id="sn4">${datas[i-1].occurTime}</span>
			<span class="span6" id="sn5">${datas[i-1].confirmStatus}&ensp;${datas[i-1].recoverStatus}</span>
			<span class="span7" id="sn6">${datas[i-1].level}级警报</span>
			<span class="spandis" id="sn7">${datas[i-1].confirmTime}</span>
			<span class="spandis" id="sn8">${datas[i-1].recoverTime}</span>
			<span class="change">修改</span>
	</li>`
		}else if(datas[i-1].type === '设备故障')
		{
			items = items+

			`<li class="spanp">
			<span class="span1" id="sn1">${alarm}</span>
			<span class="span2" id="sn2">${datas[i-1].source}</span>
			<span class="spanps53" id="sn3">${datas[i-1].type}</span>
			<span class="span4" id="sn4">${datas[i-1].occurTime}</span>
			<span class="span6" id="sn5">${datas[i-1].confirmStatus}&ensp;${datas[i-1].recoverStatus}</span>
			<span class="span7" id="sn6">${datas[i-1].level}级警报</span>
			<span class="spandis" id="sn7">${datas[i-1].confirmTime}</span>
			<span class="spandis" id="sn8">${datas[i-1].recoverTime}</span>
			<span class="change">修改</span>
	</li>`
		}else{
			items = items+
			`<li class="spanp">
			<span class="span1" id="sn1">${alarm}</span>
			<span class="span2" id="sn2">${datas[i-1].source}</span>
			<span class="spanpss3" id="sn3">${datas[i-1].type}</span>
			<span class="span4" id="sn4">${datas[i-1].occurTime}</span>
			<span class="span6" id="sn5">${datas[i-1].confirmStatus}&ensp;${datas[i-1].recoverStatus}</span>
			<span class="span7" id="sn6">${datas[i-1].level}级警报</span>
			<span class="spandis" id="sn7">${datas[i-1].confirmTime}</span>
			<span class="spandis" id="sn8">${datas[i-1].recoverTime}</span>
			<span class="change">修改</span>
	</li>`
		}
		}else {
			if(datas[i-1].type === '电源电压不稳定')
		{
			items = items+

			`<li class="spansp">
			<span class="span1" id="sn1">${alarm}</span>
			<span class="span2" id="sn2">${datas[i-1].source}</span>
			<span class="spans3" id="sn3">${datas[i-1].type}</span>
			<span class="span4" id="sn4">${datas[i-1].occurTime}</span>
			<span class="span6" id="sn5">${datas[i-1].confirmStatus}&ensp;${datas[i-1].recoverStatus}</span>
			<span class="span7" id="sn6">${datas[i-1].level}级警报</span>
			<span class="spandis" id="sn7">${datas[i-1].confirmTime}</span>
			<span class="spandis" id="sn8">${datas[i-1].recoverTime}</span>
			<span class="change" >修改</span>
	</li>`
		}else if(datas[i-1].type === '压力异常')
		{
			items = items+

			`<li class="spansp">
			<span class="span1" id="sn1">${alarm}</span>
			<span class="span2" id="sn2">${datas[i-1].source}</span>
			<span class="spanps3" id="sn3">${datas[i-1].type}</span>
			<span class="span4" id="sn4">${datas[i-1].occurTime}</span>
			<span class="span6" id="sn5">${datas[i-1].confirmStatus}&ensp;${datas[i-1].recoverStatus}</span>
			<span class="span7" id="sn6">${datas[i-1].level}级警报</span>
			<span class="spandis" id="sn7">${datas[i-1].confirmTime}</span>
			<span class="spandis" id="sn8">${datas[i-1].recoverTime}</span>
			<span class="change">修改</span>
	</li>`
		}else if(datas[i-1].type === '湿度过高')
		{
			items = items+

			`<li class="spansp">
			<span class="span1" id="sn1">${alarm}</span>
			<span class="span2" id="sn2">${datas[i-1].source}</span>
			<span class="spanps23" id="sn3">${datas[i-1].type}</span>
			<span class="span4" id="sn4">${datas[i-1].occurTime}</span>
			<span class="span6" id="sn5">${datas[i-1].confirmStatus}&ensp;${datas[i-1].recoverStatus}</span>
			<span class="span7" id="sn6">${datas[i-1].level}级警报</span>
			<span class="spandis" id="sn7">${datas[i-1].confirmTime}</span>
			<span class="spandis" id="sn8">${datas[i-1].recoverTime}</span>
			<span class="change">修改</span>
	</li>`
		}else  if(datas[i-1].type === '温度过高')
		{
			items = items+

			`<li class="spansp">
			<span class="span1" id="sn1">${alarm}</span>
			<span class="span2" id="sn2">${datas[i-1].source}</span>
			<span class="spanps33" id="sn3">${datas[i-1].type}</span>
			<span class="span4" id="sn4">${datas[i-1].occurTime}</span>
			<span class="span6" id="sn5">${datas[i-1].confirmStatus}&ensp;${datas[i-1].recoverStatus}</span>
			<span class="span7" id="sn6">${datas[i-1].level}级警报</span>
			<span class="spandis" id="sn7">${datas[i-1].confirmTime}</span>
			<span class="spandis" id="sn8">${datas[i-1].recoverTime}</span>
			<span class="change">修改</span>
	</li>`
		}else if(datas[i-1].type === '传感器失效')
		{
			items = items+

			`<li class="spansp">
			<span class="span1" id="sn1">${alarm}</span>
			<span class="span2" id="sn2">${datas[i-1].source}</span>
			<span class="spanps43" id="sn3">${datas[i-1].type}</span>
			<span class="span4" id="sn4">${datas[i-1].occurTime}</span>
			<span class="span6" id="sn5">${datas[i-1].confirmStatus}&ensp;${datas[i-1].recoverStatus}</span>
			<span class="span7" id="sn6">${datas[i-1].level}级警报</span>
			<span class="spandis" id="sn7">${datas[i-1].confirmTime}</span>
			<span class="spandis" id="sn8">${datas[i-1].recoverTime}</span>
			<span class="change">修改</span>
	</li>`
		}else if(datas[i-1].type === '设备故障')
		{
			items = items+

			`<li class="spansp">
			<span class="span1" id="sn1">${alarm}</span>
			<span class="span2" id="sn2">${datas[i-1].source}</span>
			<span class="spanps53" id="sn3">${datas[i-1].type}</span>
			<span class="span4" id="sn4">${datas[i-1].occurTime}</span>
			<span class="span6" id="sn5">${datas[i-1].confirmStatus}&ensp;${datas[i-1].recoverStatus}</span>
			<span class="span7" id="sn6">${datas[i-1].level}级警报</span>
			<span class="spandis" id="sn7">${datas[i-1].confirmTime}</span>
			<span class="spandis" id="sn8">${datas[i-1].recoverTime}</span>
			<span class="change">修改</span>
	</li>`
		}else{
			items = items+
			`<li class="spansp">
			<span class="span1" id="sn1">${alarm}</span>
			<span class="span2" id="sn2">${datas[i-1].source}</span>
			<span class="spanpss3" id="sn3">${datas[i-1].type}</span>
			<span class="span4" id="sn4">${datas[i-1].occurTime}</span>
			<span class="span6" id="sn5">${datas[i-1].confirmStatus}&ensp;${datas[i-1].recoverStatus}</span>
			<span class="span7" id="sn6">${datas[i-1].level}级警报</span>
			<span class="spandis" id="sn7">${datas[i-1].confirmTime}</span>
			<span class="spandis" id="sn8">${datas[i-1].recoverTime}</span>
			<span class="change">修改</span>
	</li>`
		}
		}
	}
	document.querySelector('.allspancontent').innerHTML = items
	update()
}

//分页器(有限制的实时报警)
function page(pageNum) {
	const pageSize = 30
	// const totalPage = parseInt((total % pageSize == 0) ? (total / pageSize) : (total / pageSize + 1))


	//实时警报展示
	myAxios({
		url:baseURL+'/alarm/selectAllAlarmPaged',
		method:'POST',
		data:{
			pageNum,
			pageSize
		}
	}).then(result => {
		console.log(result);
		const datas = result.data.records
		console.log(datas);
		show(datas)
	}).catch(error => {
		console.dir(error)
	})
}

	let day1 = ''
	let day2 = ''
	let day3 = ''
	console.log(dayTime);
	console.log(daymonth);
	if ((daymonth === 1 || daymonth === 3 || daymonth === 5 || daymonth === 7 || daymonth === 8 || daymonth === 10 || daymonth === 12) && dayTime === 1)
	{
		day1 = '2024'+'-'+daymonth+'-'+dayTime
		day2 = '2024'+'-'+(daymonth-1)+'-'+'31'
		day3 = '2024'+'-'+(daymonth-1)+'-'+'30'
		console.log(day2);
		console.log(day1);
		console.log(day3);
	}else if ((daymonth === 1 || daymonth === 3 || daymonth === 5 || daymonth === 7 || daymonth === 8 || daymonth === 10 || daymonth === 12) && dayTime === 2)
	{
		day1 = '2024'+'-'+daymonth+'-'+dayTime
		day2 = '2024'+'-'+(daymonth-0)+'-'+'1'
		day3 = '2024'+'-'+(daymonth-1)+'-'+'31'
		console.log(day2);
		console.log(day1);
		console.log(day3);
	}else if ((daymonth === 2 || daymonth === 4 || daymonth === 6 || daymonth === 9 || daymonth === 11) && dayTime === 1)
	{
		day1 = '2024'+'-'+daymonth+'-'+dayTime
		day2 = '2024'+'-'+(daymonth-1)+'-'+'30'
		day3 = '2024'+'-'+(daymonth-1)+'-'+'29'
		console.log(day2);
		console.log(day1);
		console.log(day3);
	}else if ((daymonth === 2 || daymonth === 4 || daymonth === 6 || daymonth === 9 || daymonth === 11) && dayTime === 2)
	{
		day1 = '2024'+'-'+daymonth+'-'+dayTime
		day2 = '2024'+'-'+daymonth+'-'+'1'
		day3 = '2024'+'-'+(daymonth-1)+'-'+'30'
		console.log(day2);
		console.log(day1);
		console.log(day3);
	}else {
		day1 = '2024'+'-'+daymonth+'-'+(dayTime-0)
		day2 = '2024'+'-'+daymonth+'-'+(dayTime-1)
		day3 = '2024'+'-'+daymonth+'-'+(dayTime-2)
		console.log(day2);
		console.log(day1);
		console.log(day3);
	}

	let data1 = ''
	let data2 = ''
	let data3 = ''
	let data4 = ''
	let data5 = ''
	let data6 = ''
function cp(day,data)
{
	if(day === day1) data1 = data
	if(day === day2) data2 = data
	if(day === day3) data3 = data
}
function dp(day,data)
{
	if(day === day1) data4 = data1 - data
	if(day === day2) data5 = data2 - data
	if(day === day3) data6 = data3 - data
}

//当天发生总报警数
function allalarm(day)
{
	return new Promise((resolve, reject) => {
		myAxios({
			url:baseURL+'/alarm/selectAlarmNumsOneDay',
			method:'POST',
			data:{
				time:day
			}
		}).then(result => {
			console.log(result.data);
			cp(day,result.data)
		}).catch(error => {
			console.dir(error)
		})
  })
}

function realalarm(day)
{
	return new Promise((resolve, reject) => {
		myAxios({
			url:baseURL+'/alarm/selectRealtimeAlarmNumsOneDay',
			method:'POST',
			data:{
				time:day
			}
		}).then(result => {
			dp(day,result.data)
		}).catch(error => {
			console.dir(error)
		})
  })
}
Promise.all([
	allalarm(day1),
	allalarm(day2),
	allalarm(day3),
	realalarm(day1),
	realalarm(day2),
	realalarm(day3)
]).then(() => {
  // console.log(data1, data2, data3, data4, data5, data6, data7)
	echartsconstrast()
}).catch(error => {
  console.dir(error)
})

//对比实时与历史警报
function echartsconstrast()
{
	const dataday2 = [22, 32, 15]
	const dataday = [56, 63, 51]
	let element = document.querySelector('.contrast')
	if(element)
	{
		let myChart = echarts.init(element);
    let option = {
      title: {
        // text: '不同条件下的数据对比'
      },
			legend: {
        data: ['当天发生总警报数', '当天确认恢复警报数'],
				// origin:'vertical',
				right:'0%',
				top:'13.5%',
				textStyle:{
					color:'#fff'
				}
      },
      xAxis: {
        type: 'value'
      },
      yAxis: {
        type: 'category',
        data: [day1, day2, day3]
      },
      series: [
        {
          name: '当天发生总警报数',
          type: 'bar',
          data: dataday,
          barWidth: '40%',
          orientation: 'horizontal'
        },
        {
          name: '当天确认恢复警报数',
          type: 'bar',
          data: dataday2,
          barWidth: '40%',
          orientation: 'horizontal'
        }
      ]
    };

    myChart.setOption(option);
	}else {
			console.log('找不到 #myChart 元素');
	}
}
echartsconstrast()

//一次返回4条当天的历史警报
function historyalarm(day)
{
	console.log("2024-10-09");
	myAxios({
		url: baseURL + '/alarm/selectHistoricalAlarmOneDay',
		method:'POST',
		data:{
			time:"2024-10-09"
		}
	}).then(result => {
		console.log(result);
		const length = result.data.length
		let item = ''
		let i = 1
		let k = 1
		let max = 0
		if(length%4 === 0 && length > 0)
		{
			max = length/4
		}else {
			max = length/4+1
		}
				if( length === 1)
				{
					item = item +
					`
					<div class="sucbot active">
					<div class="suc">
							<div class="sucleft1"></div>
							<div class="sucmiddle">
									<div class="sucpart">
											<div class="sucname">${result.data[i-1].source}</div>
											<div class="suclevel">${result.data[i-1].level}级警报</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[i-1].occurTime} 发生</div>
											<div class="suctimes">${result.data[i-1].confirmTime} 确认</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[i-1].recoverTime} 恢复</div>
											<div class="suctimess">${result.data[i-1].type}</div>
									</div>
							</div>
							<div class="sucright">
									<img src="./img/agree.png" class="sucrightimg">
									<div class="sucrightstatic">已确认恢复</div>
							</div>
					</div>
			</div>
					`
				}else if( length === 2)
				{
					item = item +
					`
					<div class="sucbot active">
					<div class="suc">
							<div class="sucleft1"></div>
							<div class="sucmiddle">
									<div class="sucpart">
											<div class="sucname">${result.data[i-1].source}</div>
											<div class="suclevel">${result.data[i-1].level}级警报</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[i-1].occurTime} 发生</div>
											<div class="suctimes">${result.data[i-1].confirmTime} 确认</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[i-1].recoverTime} 恢复</div>
											<div class="suctimess">${result.data[i-1].type}</div>
									</div>
							</div>
							<div class="sucright">
									<img src="./img/agree.png" class="sucrightimg">
									<div class="sucrightstatic">已确认恢复</div>
							</div>
					</div>
					<div class="suc">
                            <div class="sucleft2"></div>
                            <div class="sucmiddle">
									<div class="sucpart">
											<div class="sucname">${result.data[i].source}</div>
											<div class="suclevel">${result.data[i].level}级警报</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[i].occurTime}发生</div>
											<div class="suctimes">${result.data[i].confirmTime}  确认</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[i].recoverTime} 恢复</div>
											<div class="suctimess">${result.data[i].type}</div>
									</div>
							</div>
                            <div class="sucright">
                                <img src="./img/agree.png" class="sucrightimg">
                                <div class="sucrightstatic">已确认恢复</div>
                            </div>
                        </div>
			</div>
					`
				}else if( length === 3)
				{
					item = item +
					`
					<div class="sucbot active">
					<div class="suc">
							<div class="sucleft1"></div>
							<div class="sucmiddle">
									<div class="sucpart">
											<div class="sucname">${result.data[i-1].source}</div>
											<div class="suclevel">${result.data[i-1].level}级警报</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[i-1].occurTime}  发生</div>
											<div class="suctimes">${result.data[i-1].confirmTime}  确认</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[i-1].recoverTime} 恢复</div>
											<div class="suctimess">${result.data[i-1].type}</div>
									</div>
							</div>
							<div class="sucright">
									<img src="./img/agree.png" class="sucrightimg">
									<div class="sucrightstatic">已确认恢复</div>
							</div>
					</div>
					<div class="suc">
                            <div class="sucleft2"></div>
                            <div class="sucmiddle">
									<div class="sucpart">
											<div class="sucname">${result.data[i].source}</div>
											<div class="suclevel">${result.data[i].level}级警报</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[i].occurTime} 发生</div>
											<div class="suctimes">${result.data[i].confirmTime}  确认</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[i].recoverTime} 恢复</div>
											<div class="suctimess">${result.data[i].type}</div>
									</div>
							</div>
                            <div class="sucright">
                                <img src="./img/agree.png" class="sucrightimg">
                                <div class="sucrightstatic">已确认恢复</div>
                            </div>
                        </div>
												<div class="suc">
							<div class="sucleft3"></div>
							<div class="sucmiddle">
									<div class="sucpart">
											<div class="sucname">${result.data[i+1].source}</div>
											<div class="suclevel">${result.data[i+1].level}级警报</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[i+1].occurTime} 发生</div>
											<div class="suctimes">${result.data[i+1].confirmTime}  确认</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[i+1].recoverTime} 恢复</div>
											<div class="suctimess">${result.data[i+1].type}</div>
									</div>
							</div>
							<div class="sucright">
									<img src="./img/agree.png" class="sucrightimg">
									<div class="sucrightstatic">已确认恢复</div>
							</div>
					</div>
			</div>
					`
				}else if( length === 4)
				{
					item = item +
					`
					<div class="sucbot active">
					<div class="suc">
							<div class="sucleft1"></div>
							<div class="sucmiddle">
									<div class="sucpart">
											<div class="sucname">${result.data[i-1].source}</div>
											<div class="suclevel">${result.data[i-1].level}级警报</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[i-1].occurTime} 发生</div>
											<div class="suctimes">${result.data[i-1].confirmTime} 确认</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[i-1].recoverTime}  恢复</div>
											<div class="suctimess">${result.data[i-1].type}</div>
									</div>
							</div>
							<div class="sucright">
									<img src="./img/agree.png" class="sucrightimg">
									<div class="sucrightstatic">已确认恢复</div>
							</div>
					</div>
					<div class="suc">
                            <div class="sucleft2"></div>
                            <div class="sucmiddle">
									<div class="sucpart">
											<div class="sucname">${result.data[i].source}</div>
											<div class="suclevel">${result.data[i].level}级警报</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[i].occurTime} 发生</div>
											<div class="suctimes">${result.data[i].confirmTime}  确认</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[i].recoverTime} 恢复</div>
											<div class="suctimess">${result.data[i].type}</div>
									</div>
							</div>
                            <div class="sucright">
                                <img src="./img/agree.png" class="sucrightimg">
                                <div class="sucrightstatic">已确认恢复</div>
                            </div>
                        </div>
												<div class="suc">
							<div class="sucleft3"></div>
							<div class="sucmiddle">
									<div class="sucpart">
											<div class="sucname">${result.data[i+1].source}</div>
											<div class="suclevel">${result.data[i+1].level}级警报</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[i+1].occurTime} 发生</div>
											<div class="suctimes">${result.data[i+1].confirmTime} 确认</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[i+1].recoverTime} 恢复</div>
											<div class="suctimess">${result.data[i+1].type}</div>
									</div>
							</div>
							<div class="sucright">
									<img src="./img/agree.png" class="sucrightimg">
									<div class="sucrightstatic">已确认恢复</div>
							</div>
					</div>
					<div class="suc">
					<div class="sucleft4"></div>
					<div class="sucmiddle">
							<div class="sucpart">
									<div class="sucname">${result.data[i+2].source}</div>
									<div class="suclevel">${result.data[i+2].level}级警报</div>
							</div>
							<div class="sucpart">
									<div class="suctime">${result.data[i+2].occurTime} 发生</div>
									<div class="suctimes">${result.data[i+2].confirmTime} 确认</div>
							</div>
							<div class="sucpart">
									<div class="suctime">${result.data[i+2].recoverTime} 恢复</div>
									<div class="suctimess">${result.data[i+2].type}</div>
							</div>
					</div>
					<div class="sucright">
							<img src="./img/agree.png" class="sucrightimg">
							<div class="sucrightstatic">已确认恢复</div>
					</div>
			</div>
			</div>
					`
				}else {
					for( k = 1 ; k<=max ; k++)
					{
					   if(k===max && length%4 === 1)
						{
							item = item +
							`
							<div class="sucbot">
							<div class="suc">
									<div class="sucleft1"></div>
									<div class="sucmiddle">
											<div class="sucpart">
													<div class="sucname">${result.data[(k-1)*4].source}</div>
													<div class="suclevel">${result.data[(k-1)*4].level}级警报</div>
											</div>
											<div class="sucpart">
													<div class="suctime">${result.data[(k-1)*4].occurTime} 发生</div>
													<div class="suctimes">${result.data[(k-1)*4].confirmTime} 确认</div>
											</div>
											<div class="sucpart">
													<div class="suctime">${result.data[(k-1)*4].recoverTime} 恢复</div>
													<div class="suctimess">${result.data[(k-1)*4].type}</div>
											</div>
									</div>
									<div class="sucright">
											<img src="./img/agree.png" class="sucrightimg">
											<div class="sucrightstatic">已确认恢复</div>
									</div>
							</div>
					</div>
							`
						}else if(k===max && length%4 === 2)
						{
							item = item +
							`
							<div class="sucbot">
							<div class="suc">
									<div class="sucleft1"></div>
									<div class="sucmiddle">
											<div class="sucpart">
													<div class="sucname">${result.data[(k-1)*4].source}</div>
													<div class="suclevel">${result.data[(k-1)*4].level}级警报</div>
											</div>
											<div class="sucpart">
													<div class="suctime">${result.data[(k-1)*4].occurTime}  发生</div>
													<div class="suctimes">${result.data[(k-1)*4].confirmTime} 确认</div>
											</div>
											<div class="sucpart">
													<div class="suctime">${result.data[(k-1)*4].recoverTime} 恢复</div>
													<div class="suctimess">${result.data[(k-1)*4].type}</div>
											</div>
									</div>
									<div class="sucright">
											<img src="./img/agree.png" class="sucrightimg">
											<div class="sucrightstatic">已确认恢复</div>
									</div>
							</div>
							<div class="suc">
									<div class="sucleft2"></div>
									<div class="sucmiddle">
											<div class="sucpart">
													<div class="sucname">${result.data[(k-1)*4+1].source}</div>
													<div class="suclevel">${result.data[(k-1)*4+1].level}级警报</div>
											</div>
											<div class="sucpart">
													<div class="suctime">${result.data[(k-1)*4+1].occurTime}  发生</div>
													<div class="suctimes">${result.data[(k-1)*4+1].confirmTime}确认</div>
											</div>
											<div class="sucpart">
													<div class="suctime">${result.data[(k-1)*4+1].recoverTime} 恢复</div>
													<div class="suctimess">${result.data[(k-1)*4+1].type}</div>
											</div>
									</div>
									<div class="sucright">
											<img src="./img/agree.png" class="sucrightimg">
											<div class="sucrightstatic">已确认恢复</div>
									</div>
							</div>
					</div>
							`
						}else if(k===max && length%4 === 3)
						{
							item = item +
							`
							<div class="sucbot">
							<div class="suc">
									<div class="sucleft1"></div>
									<div class="sucmiddle">
											<div class="sucpart">
													<div class="sucname">${result.data[(k-1)*4].source}</div>
													<div class="suclevel">${result.data[(k-1)*4].level}级警报</div>
											</div>
											<div class="sucpart">
													<div class="suctime">${result.data[(k-1)*4].occurTime}发生</div>
													<div class="suctimes">${result.data[(k-1)*4].confirmTime} 确认</div>
											</div>
											<div class="sucpart">
													<div class="suctime">${result.data[(k-1)*4].recoverTime} 恢复</div>
													<div class="suctimess">${result.data[(k-1)*4].type}</div>
											</div>
									</div>
									<div class="sucright">
											<img src="./img/agree.png" class="sucrightimg">
											<div class="sucrightstatic">已确认恢复</div>
									</div>
							</div>
							<div class="suc">
									<div class="sucleft2"></div>
									<div class="sucmiddle">
											<div class="sucpart">
													<div class="sucname">${result.data[(k-1)*4+1].source}</div>
													<div class="suclevel">${result.data[(k-1)*4+1].level}级警报</div>
											</div>
											<div class="sucpart">
													<div class="suctime">${result.data[(k-1)*4+1].occurTime} 发生</div>
													<div class="suctimes">${result.data[(k-1)*4+1].confirmTime} 确认</div>
											</div>
											<div class="sucpart">
													<div class="suctime">${result.data[(k-1)*4+1].recoverTime} 恢复</div>
													<div class="suctimess">${result.data[(k-1)*4+1].type}</div>
											</div>
									</div>
									<div class="sucright">
											<img src="./img/agree.png" class="sucrightimg">
											<div class="sucrightstatic">已确认恢复</div>
									</div>
							</div>
							<div class="suc">
									<div class="sucleft3"></div>
									<div class="sucmiddle">
											<div class="sucpart">
													<div class="sucname">${result.data[(k-1)*4+2].source}</div>
													<div class="suclevel">${result.data[(k-1)*4+2].level}级警报</div>
											</div>
											<div class="sucpart">
													<div class="suctime">${result.data[(k-1)*4+2].occurTime} 发生</div>
													<div class="suctimes">${result.data[(k-1)*4+2].confirmTime} 确认</div>
											</div>
											<div class="sucpart">
													<div class="suctime">${result.data[(k-1)*4+2].recoverTime} 恢复</div>
													<div class="suctimess">${result.data[(k-1)*4+2].type}</div>
											</div>
									</div>
									<div class="sucright">
											<img src="./img/agree.png" class="sucrightimg">
											<div class="sucrightstatic">已确认恢复</div>
									</div>
							</div>
					</div>
							`
						}else {
							item = item +
						`
					<div class="sucbot active">
					<div class="suc">
							<div class="sucleft1"></div>
							<div class="sucmiddle">
									<div class="sucpart">
											<div class="sucname">${result.data[k-1].source}</div>
											<div class="suclevel">${result.data[k-1].level}级警报</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[k-1].occurTime} 发生</div>
											<div class="suctimes">${result.data[k-1].confirmTime} 确认</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[k-1].recoverTime} 恢复</div>
											<div class="suctimess">${result.data[k-1].type}</div>
									</div>
							</div>
							<div class="sucright">
									<img src="./img/agree.png" class="sucrightimg">
									<div class="sucrightstatic">已确认恢复</div>
							</div>
					</div>
					<div class="suc">
                            <div class="sucleft2"></div>
                            <div class="sucmiddle">
									<div class="sucpart">
											<div class="sucname">${result.data[k].source}</div>
											<div class="suclevel">${result.data[k].level}级警报</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[k].occurTime} 发生</div>
											<div class="suctimes">${result.data[k].confirmTime} 确认</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[k].recoverTime} 恢复</div>
											<div class="suctimess">${result.data[k].type}</div>
									</div>
							</div>
                            <div class="sucright">
                                <img src="./img/agree.png" class="sucrightimg">
                                <div class="sucrightstatic">已确认恢复</div>
                            </div>
                        </div>
												<div class="suc">
							<div class="sucleft3"></div>
							<div class="sucmiddle">
									<div class="sucpart">
											<div class="sucname">${result.data[k+1].source}</div>
											<div class="suclevel">${result.data[k+1].level}级警报</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[k+1].occurTime} 发生</div>
											<div class="suctimes">${result.data[k+1].confirmTime} 确认</div>
									</div>
									<div class="sucpart">
											<div class="suctime">${result.data[k+1].recoverTime} 恢复</div>
											<div class="suctimess">${result.data[k+1].type}</div>
									</div>
							</div>
							<div class="sucright">
									<img src="./img/agree.png" class="sucrightimg">
									<div class="sucrightstatic">已确认恢复</div>
							</div>
					</div>
					<div class="suc">
					<div class="sucleft4"></div>
					<div class="sucmiddle">
							<div class="sucpart">
									<div class="sucname">${result.data[k+2].source}</div>
									<div class="suclevel">${result.data[k+2].level}级警报</div>
							</div>
							<div class="sucpart">
									<div class="suctime">${result.data[k+2].occurTime} 发生</div>
									<div class="suctimes">${result.data[k+2].confirmTime} 确认</div>
							</div>
							<div class="sucpart">
									<div class="suctime">${result.data[k+2].recoverTime} 恢复</div>
									<div class="suctimess">${result.data[k+2].type}</div>
							</div>
					</div>
					<div class="sucright">
							<img src="./img/agree.png" class="sucrightimg">
							<div class="sucrightstatic">已确认恢复</div>
					</div>
			</div>
			</div>
					`
						}

					}
				}
				item = item + `<div class="tjbotBottom"></div>`
				// console.log(item);
				document.querySelector('.tjbot').innerHTML = item
				suc()
	}).catch(error => {
		console.dir(error)
	})
}
// historyalarm("2024-07-27")
historyalarm(day1)

//问题反馈
function question()
{
	document.querySelector('#questionbut').addEventListener('click',() => {
		if(document.querySelector('.Pop-ups').style.display=='none'||!document.querySelector('.Pop-ups').style.display){
			document.querySelector('.Pop-ups').style.display = 'block'
			document.querySelector('.kailong').style.display = 'block'
			console.log(document.querySelector('.editorbuttons'));
			document.querySelector('.editorbuttons').addEventListener('click',() => {
		console.log('成功');
		document.querySelector('.Pop-ups').style.display = 'none'
		document.querySelector('.kailong').style.display = 'none'
		document.querySelector('.sucesses').style.display = 'block'
		setInterval(function() {
			document.querySelector('.sucesses').style.display = 'none'
		},1500)
	})
	}else{
			document.querySelector('.Pop-ups').style.display = 'none'
			document.querySelector('.kailong').style.display = 'none'
	}
	})

}
question()

//版本
function setup()
{
	document.querySelector('#setup').addEventListener('click',() => {
		if(document.querySelector('.setup').style.display=='none'||!document.querySelector('.setup').style.display){
			document.querySelector('.setup').style.display = 'block'
			document.querySelector('.kailongs').style.display = 'block'
	}else{
			document.querySelector('.setup').style.display = 'none'
			document.querySelector('.kailongs').style.display = 'none'
	}
	})
}
setup()

function suc()
{
	const sucbot = document.querySelectorAll('.sucbot')
	let currentIndex = 0;
//轮播历史警报
function showCarouselItem(index)
{
	sucbot.forEach((item, i) => {
		if (i === index) {
			item.classList.add('active');
		} else {
			item.classList.remove('active');
		}
	});
}

function nextCarouselItem() {
	currentIndex++;
	if (currentIndex >= sucbot.length) {
		currentIndex = 0;
	}
	showCarouselItem(currentIndex);
}

setInterval(nextCarouselItem, 2000);
}

//智能问答
function gpt()
{
	document.querySelector('#gpt').addEventListener('click',() => {
		if(document.querySelector('.gpt').style.display=='none'||!document.querySelector('.gpt').style.display){
			document.querySelector('.gpt').style.display = 'block'
			document.querySelector('.kailongss').style.display = 'block'
	}else{
			document.querySelector('.gpt').style.display = 'none'
			document.querySelector('.kailongss').style.display = 'none'
	}
	})

	//点击常见问题实现问答
	document.querySelector('#pro1').addEventListener('click',() => {
		document.querySelector('.questionBody').innerHTML = document.querySelector('.questionBody').innerHTML+
		`
		<div class="question">
		<img src="./img/gpticon.png" class="gpticon" >
			<div class="gptQuestion">如何联系人工客服？</div>
		</div>
		`
		gptic("如何联系人工客服？")
	})
	document.querySelector('#pro2').addEventListener('click',() => {
		document.querySelector('.questionBody').innerHTML = document.querySelector('.questionBody').innerHTML+
		`
		<div class="question">
		<img src="./img/gpticon.png" class="gpticon" >
			<div class="gptQuestion">最近十条实时警报在哪展示？</div>
		</div>
		`
		gptic("最近十条实时警报在哪展示？")
	})
	document.querySelector('#pro3').addEventListener('click',() => {
		document.querySelector('.questionBody').innerHTML = document.querySelector('.questionBody').innerHTML+
		`
		<div class="question">
		<img src="./img/gpticon.png" class="gpticon" >
			<div class="gptQuestion">什么是工业报警系统？</div>
		</div>
		`
		gptic("什么是工业报警系统？")
	})
	document.querySelector('#pro4').addEventListener('click',() => {
		document.querySelector('.questionBody').innerHTML = document.querySelector('.questionBody').innerHTML+
		`
		<div class="question">
		<img src="./img/gpticon.png" class="gpticon" >
			<div class="gptQuestion">如何进行实时警报处理？</div>
		</div>
		`
		gptic("如何进行实时警报处理？")
	})
	document.querySelector('#pro5').addEventListener('click',() => {
		document.querySelector('.questionBody').innerHTML = document.querySelector('.questionBody').innerHTML+
		`
		<div class="question">
		<img src="./img/gpticon.png" class="gpticon" >
			<div class="gptQuestion">如何进行历史警报修改？</div>
		</div>
		`
		gptic("如何进行历史警报修改？")
	})
}
gpt()

//input标签问题显示
function clearF() {
		const db = document.querySelector('.db')
		document.querySelector('.questionBody').innerHTML = document.querySelector('.questionBody').innerHTML+
		`
		<div class="question">
		<img src="./img/gpticon.png" class="gpticon" >
			<div class="gptQuestion">${db.value}</div>
		</div>
		`
		gptic(db.value)
		db.value = ""
}

//gpt接口
function gptic(content)
{
	myAxios({
		url: baseURLGPT +'/ai/generate',
		params:{
			message:content
		}
	}).then(result => {
		console.log(result);
		document.querySelector('.questionBody').innerHTML = document.querySelector('.questionBody').innerHTML+
		`
		<div class="gptanswer">
			<img src="./img/ai.png" class="answerai">
			<div class="answer">${result.generation}</div>
		</div>
		`
	}).catch(error => {
		console.dir(error)
	})
}

//通过警报源和时间找唯一记录
function onlyone() {
	document.querySelector('.expandsure').addEventListener('click', () => {
		const form = document.querySelector('.expandform')
		const data = serialize(form, { hash: true, empty: true })
		console.log(data);
		myAxios({
			url: baseURL + '/alarm/selectAlarmBySourceAndOccurTime',
			method: 'POST',
			data
		}).then(result => {
			console.log(result.data);
			if (result.data.confirmStatus) {
				result.data.confirmStatus = '已确认'
			} else {
				result.data.confirmStatus = '未确认'
			}
			if (result.data.recoverStatus) {
				result.data.recoverStatus = '已恢复'
			} else {
				result.data.recoverStatus = '未恢复'
			}
			document.querySelector('.expandp').innerHTML = `${result.data.source}&ensp; &ensp;
																											${result.data.type}&ensp; &ensp;
																											${result.data.occurTime}&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;
																											${result.data.level}级警报&ensp;&ensp;
																											${result.data.confirmStatus}&ensp;&ensp;
																											${result.data.recoverStatus}`
		}).catch(error => {
			console.dir(error)
		})
	})
}
onlyone()

//搜索拓展框
function search() {
	document.querySelector('.rightsearch').addEventListener('mouseenter', () => {
		document.querySelector('.rightinput').style.display = 'block'
	})
	document.querySelector('.rightsearch').addEventListener('clcik', () => {
		document.querySelector('.rightinput').style.display = 'block'
	})
	document.querySelector('.rightinput').addEventListener('mouseenter', () => {
		document.querySelector('.rightinput').style.display = 'block'
	})
	document.querySelector('.rightinput').addEventListener('mouseleave', () => {
		setTimeout(() => {
			if (!document.querySelector('.rightinput input').matches(':focus')) {
				document.querySelector('.rightinput').style.display = 'none'
			}
		}, 3000)
	})
}
search()

//日历js实现
function jstime()
{
	let date = new Date() //日期对象
      let currentYear = date.getFullYear() // 当前年
      let currentMonth = date.getMonth() // 当前月
      let currentDate = date.getDate() // 当前日
      let setCurrentDateFlag = false // 是否设置了当前日背景,默认为false
      let selectDate = '' //选中日期
      // 展示年月日
      $('.show-year-month').textContent = `${currentYear}年${currentMonth + 1}月`
      // 上个月按钮
      $('.pre-date').onclick = function () {
        currentMonth = currentMonth - 1
        // 当当前月小于0，将当前月设置为11月
        if (currentMonth < 0) {
          currentMonth = 11
          currentYear = currentYear - 1
        }
        $('.show-year-month').textContent = `${currentYear}年${currentMonth + 1}月`
        setCurrentDateFlag = false
        getMultidimensionalArrayByDay(currentYear, currentMonth)
      }
      // 下个月按钮
      $('.next-date').onclick = function () {
        currentMonth = currentMonth + 1
        // 当当前月大于12,将当前月重置为1
        if (currentMonth > 12) {
          currentMonth = 1
          currentYear = currentYear + 1
        }
        $('.show-year-month').textContent = `${currentYear}年${currentMonth + 1}月`
        setCurrentDateFlag = false
        getMultidimensionalArrayByDay(currentYear, currentMonth)
      }
      // 日期选择框input
      $('.date-input').onclick = function () {
        if ($('.date').style.display == 'none' || !$('.date').style.display) {
          $('.date').style.display = 'inline-block'
          observeTdClick()
        } else {
          $('.date').style.display = 'none'
        }
      }
      // 获取年月日数组
      function getMultidimensionalArrayByDay(currentYear, currentMonth) {
        // 清除dom，重新渲染表格
        let trList = Array.from($('.date-tbody').children)
        if (trList && trList.length) {
          trList.map(item => {
            $('.date-tbody').removeChild(item)
          })
        }
        let d = new Date(currentYear, currentMonth)
        d.setDate(1)
        let startDay = d.getDay()
        let count = 0
        let nextPreMonthCount = 0
        let multidimensionalArray = []
        // 一共有6个tr，6行
        for (let i = 0; i < 6; i++) {
         // 设置列数 这里是7列
          let dayColumnList = new Array(7)
          if (i === 0) {
            //  第一行  startDay 当前月的第一天是周几 假设 startDay = 5  那么 j = 4
            // dayColumnList.length 7
            // [,,,,1,2,3]
            for (let j = startDay - 1; j < dayColumnList.length; j++) {
              dayColumnList[j] = ++count
            }
          } else {
            // 按照上述假设，此时count = 4, dayColumnList.length = 7  0,1,2,3,4,5,6
            for (let j = 0; j < dayColumnList.length; j++) {
              count++
              // 按照上述假设， getDayNumsByMonth(d.getMonth())返回值为31
              // 超过当前月天数,开始下个月天数加1填充
              if (count > getDayNumsByMonth(d.getMonth())) {
                dayColumnList[j] = ++nextPreMonthCount
              } else {
                // 没超过当前月天数的情况
                dayColumnList[j] = count
              }
            }
          }
          multidimensionalArray.push(dayColumnList)
        }
        // 填充第一行为空的日期
        let preMonthDayNums = getDayNumsByMonth(d.getMonth() - 1)
        for (let k = d.getDay() - 2; k >= 0; k--) {
          multidimensionalArray[0][k] = preMonthDayNums--
        }
        let nextMonthNums = getDayNumsByMonth(d.getMonth() + 1)
        let realCurrentYear = date.getFullYear()
        let realCurrentMonth = date.getMonth()
        // 渲染表格
        for (let q = 0; q < multidimensionalArray.length; q++) {
          let tr = document.createElement('tr')
          let item = multidimensionalArray[q]
          for (let m = 0; m < item.length; m++) {
            let td = document.createElement('td')
            td.textContent = item[m]
            // 当前年月日背景色为蓝色，颜色为白色
            if (!setCurrentDateFlag && item[m] === currentDate && realCurrentYear === currentYear && realCurrentMonth === currentMonth) {
              td.style.backgroundColor = 'blue'
              td.style.color = 'white'
              setCurrentDateFlag = true
            }
            tr.appendChild(td)
          }
          $('.date-tbody').appendChild(tr)
        }
      }
      // 获取一个月有多少天
      function getDayNumsByMonth(currentMonth) {
        let d = new Date()
        d.setMonth(currentMonth + 1)
        d.setDate(0) // 可以获取一个月有多少天
        let dayNums = d.getDate()
        return dayNums
      }
      // 监听点击选择日期事件
      function observeTdClick() {
        let tdList = Array.from(document.getElementsByTagName('td'))
        tdList.forEach(td => {
          td.onclick = function () {
            let day = td.textContent
            selectDate = `${currentYear}-${currentMonth + 1}-${day}`
            $('.date-input').value = selectDate
            $('.date').style.display = 'none'
          }
        })
      }
      function $(selector) {
        return document.querySelector(selector)
      }
      getMultidimensionalArrayByDay(currentYear, currentMonth)
}
jstime()

//点击修改
// function update()
// {
// 	document.querySelector('.editorbutton').addEventListener('click',() => {
// 		document.querySelector('.pops').style.display = 'none'
// 	})
// }
// update()

//界面对应筛选
function sys()
{
	var requestOptions = {
		method: 'POST',
		headers: myHeaders,
		body: raw,
		redirect: 'follow'
 };

 fetch("http://localhost:8080/alarm/selectCondition", requestOptions)
		.then(response => response.text())
		.then(result =>
		 console.log(JSON.parse(result))
		 )
		.catch(error => console.log('error', error));
}

//筛选功能
function choose()
{
var myHeaders = new Headers();
myHeaders.append("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzMDMzNjYxMmQ4MTI0Mjg0OWI0ODUwNWYyNmYwNGUyNCIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTcyODQ3MDg3NiwiZXhwIjoyMDQzODMwODc2fQ.a_ICOwIrC4aUpW0g1P6w7TkAK48FIQdd0OgiFFQ5Fy4");
myHeaders.append("User-Agent", "Apifox/1.0.0 (https://apifox.com)");
myHeaders.append("Content-Type", "application/json");
const form = document.querySelector('.form-select')
let data = null
document.querySelector('#s1').addEventListener('change',e => {
	console.log(e.target.value);
	 data = serialize(form,{ hash:true,empty:true})
	if(data.confirmStatus === "true")
	{
		data.confirmStatus = true
	}else if(data.confirmStatus === "false")
	{
		data.confirmStatus = false
	}else
	{
		data.confirmStatus = null
	}
	if(data.recoverStatus === "true")
	{
		data.recoverStatus = true
	}else if(data.recoverStatus === "false")
	{
		data.recoverStatus = false
	}else
	{
		data.recoverStatus = null
	}
	if(data.orderRule === "true")
	{
		data.orderRule = true
	}else if(data.orderRule === "false")
	{
		data.orderRule = false
	}else
	{
		data.orderRule = null
	}
	if(data.type === "null")
	{
		data.type = null
	}
	if(data.level === "null")
	{
		data.level = null
	}
	console.log(JSON.stringify(data));
	var raw = JSON.stringify(data);

var requestOptions = {
   method: 'POST',
   headers: myHeaders,
   body: raw,
   redirect: 'follow'
};

fetch("http://localhost:8080/alarm/selectConditionPaged", requestOptions)
   .then(response => response.text())
   .then(result =>
		{
      const parsedResult = JSON.parse(result);
			console.log(parsedResult);
      show(parsedResult.data.records)
			forpages(parsedResult.data.total)
			document.querySelector('.xj').innerHTML = `共${parsedResult.data.total}条`
   }
		)
   .catch(error => console.log('error', error));
})
document.querySelector('#s2').addEventListener('change',e => {
	console.log(e.target.value);
	 data = serialize(form,{ hash:true,empty:true})
	if(data.confirmStatus === "true")
	{
		data.confirmStatus = true
	}else if(data.confirmStatus === "false")
	{
		data.confirmStatus = false
	}else
	{
		data.confirmStatus = null
	}
	if(data.recoverStatus === "true")
	{
		data.recoverStatus = true
	}else if(data.recoverStatus === "false")
	{
		data.recoverStatus = false
	}else
	{
		data.recoverStatus = null
	}
	if(data.orderRule === "true")
	{
		data.orderRule = true
	}else if(data.orderRule === "false")
	{
		data.orderRule = false
	}else
	{
		data.orderRule = null
	}
	if(data.type === "null")
	{
		data.type = null
	}
	if(data.level === "null")
	{
		data.level = null
	}
	console.log(JSON.stringify(data));
	var raw = JSON.stringify(data);

var requestOptions = {
   method: 'POST',
   headers: myHeaders,
   body: raw,
   redirect: 'follow'
};

fetch("http://localhost:8080/alarm/selectConditionPaged", requestOptions)
   .then(response => response.text())
   .then(result =>
		{
      const parsedResult = JSON.parse(result);
			console.log(parsedResult.data);
			console.log(parsedResult.data.total);
      show(parsedResult.data.records)
			console.log(parsedResult.data.total);
			forpages(parsedResult.data.total)
			document.querySelector('.xj').innerHTML = `共${parsedResult.data.total}条`
   }
		)
   .catch(error => console.log('error', error));
})
document.querySelector('#s3').addEventListener('change',e => {
	console.log(e.target.value);
	 data = serialize(form,{ hash:true,empty:true})
	if(data.confirmStatus === "true")
	{
		data.confirmStatus = true
	}else if(data.confirmStatus === "false")
	{
		data.confirmStatus = false
	}else
	{
		data.confirmStatus = null
	}
	if(data.recoverStatus === "true")
	{
		data.recoverStatus = true
	}else if(data.recoverStatus === "false")
	{
		data.recoverStatus = false
	}else
	{
		data.recoverStatus = null
	}
	if(data.orderRule === "true")
	{
		data.orderRule = true
	}else if(data.orderRule === "false")
	{
		data.orderRule = false
	}else
	{
		data.orderRule = null
	}
	if(data.type === "null")
	{
		data.type = null
	}
	if(data.level === "null")
	{
		data.level = null
	}
	console.log(JSON.stringify(data));
	var raw = JSON.stringify(data);

var requestOptions = {
   method: 'POST',
   headers: myHeaders,
   body: raw,
   redirect: 'follow'
};

fetch("http://localhost:8080/alarm/selectConditionPaged", requestOptions)
   .then(response => response.text())
   .then(result =>
		{
      const parsedResult = JSON.parse(result);
			console.log(parsedResult.data.records);
      show(parsedResult.data.records)
			console.log(parsedResult.data.total);
			forpages(parsedResult.data.total)
			document.querySelector('.xj').innerHTML = `共${parsedResult.data.total}条`
   }
		)
   .catch(error => console.log('error', error));
})
document.querySelector('#s4').addEventListener('change',e => {
	console.log(e.target.value);
	 data = serialize(form,{ hash:true,empty:true})
	if(data.confirmStatus === "true")
	{
		data.confirmStatus = true
	}else if(data.confirmStatus === "false")
	{
		data.confirmStatus = false
	}else
	{
		data.confirmStatus = null
	}
	if(data.recoverStatus === "true")
	{
		data.recoverStatus = true
	}else if(data.recoverStatus === "false")
	{
		data.recoverStatus = false
	}else
	{
		data.recoverStatus = null
	}
	if(data.orderRule === "true")
	{
		data.orderRule = true
	}else if(data.orderRule === "false")
	{
		data.orderRule = false
	}else
	{
		data.orderRule = null
	}
	if(data.type === "null")
	{
		data.type = null
	}
	if(data.level === "null")
	{
		data.level = null
	}
	console.log(JSON.stringify(data));
	var raw = JSON.stringify(data);

var requestOptions = {
   method: 'POST',
   headers: myHeaders,
   body: raw,
   redirect: 'follow'
};

fetch("http://localhost:8080/alarm/selectConditionPaged", requestOptions)
   .then(response => response.text())
   .then(result =>
		{
      const parsedResult = JSON.parse(result);
			console.log(parsedResult.data.records);
      show(parsedResult.data.records)
			console.log(parsedResult.data.total);
			forpages(parsedResult.data.total)
			document.querySelector('.xj').innerHTML = `共${parsedResult.data.total}条`
   }
		)
   .catch(error => console.log('error', error));
})
document.querySelector('#s5').addEventListener('change',e => {
	console.log(e.target.value);
	 data = serialize(form,{ hash:true,empty:true})
	if(data.confirmStatus === "true")
	{
		data.confirmStatus = true
	}else if(data.confirmStatus === "false")
	{
		data.confirmStatus = false
	}else
	{
		data.confirmStatus = null
	}
	if(data.recoverStatus === "true")
	{
		data.recoverStatus = true
	}else if(data.recoverStatus === "false")
	{
		data.recoverStatus = false
	}else
	{
		data.recoverStatus = null
	}
	if(data.orderRule === "true")
	{
		data.orderRule = true
	}else if(data.orderRule === "false")
	{
		data.orderRule = false
	}else
	{
		data.orderRule = null
	}
	if(data.type === "null")
	{
		data.type = null
	}
	if(data.level === "null")
	{
		data.level = null
	}
	console.log(JSON.stringify(data));
	var raw = JSON.stringify(data);

var requestOptions = {
   method: 'POST',
   headers: myHeaders,
   body: raw,
   redirect: 'follow'
};

fetch("http://localhost:8080/alarm/selectConditionPaged", requestOptions)
   .then(response => response.text())
   .then(result =>
		{
      const parsedResult = JSON.parse(result);
			console.log(parsedResult.data.records);
      show(parsedResult.data.records)
			console.log(parsedResult.data.total);
			forpages(parsedResult.data.total)
			document.querySelector('.xj').innerHTML = `共${parsedResult.data.total}条`
   }
		)
   .catch(error => console.log('error', error));
})
}
choose()

//分页循环
function forpages(total) {
	const pageSize = 30
	const totalPage = parseInt((total % pageSize == 0) ? (total / pageSize) : (total / pageSize + 1))
	const pagination = document.querySelector('.pagination')
	pagination.innerHTML = ''
	if(totalPage === 1)
	{
		pagination.innerHTML = pagination.innerHTML+`<li data-page="0"><a href="#" class="le">上一页</a></li>`
		pagination.innerHTML = pagination.innerHTML+`<li data-page="1"><a href="#" class="li2 centeractive">1</a></li>`
		pagination.innerHTML = pagination.innerHTML+`<li data-page="100"><a href="#" class="ri">下一页</a></li>`
		// document.querySelector('.center').classList.add('centers')
		// document.querySelector('.centers').classList.remove('center')
	}else if(totalPage === 2){
		pagination.innerHTML = pagination.innerHTML+`<li data-page="0"><a href="#" class="le">上一页</a></li>`
		pagination.innerHTML = pagination.innerHTML+`<li data-page="1"><a href="#" class="li2 centeractive">1</a></li>`
		pagination.innerHTML = pagination.innerHTML+`<li data-page="2"><a href="#" class="li2">2</a></li>`
		pagination.innerHTML = pagination.innerHTML+`<li data-page="100"><a href="#" class="ri">下一页</a></li>`
		document.querySelector('.center').classList.add('centerss')
		document.querySelector('.centerss').classList.remove('center')
		document.querySelector('[data-page="0"]').addEventListener('click',() => {
			let thispg = document.querySelector('.centeractive').closest('LI').dataset.page
			if(thispg > 1)
			{
				thispg--
				document.querySelector('.centeractive').classList.remove('centeractive')
			}
			document.querySelector(`[data-page="${thispg}"] a`).classList.add('centeractive')
		})
		document.querySelector('[data-page="100"]').addEventListener('click',() => {
			let thispg = document.querySelector('.centeractive').closest('LI').dataset.page
			if(thispg < totalPage)
			{
				thispg++
				document.querySelector('.centeractive').classList.remove('centeractive')
			}
			document.querySelector(`[data-page="${thispg}"] a`).classList.add('centeractive')
		})
		document.querySelector('[data-page="1"]').addEventListener('click',() => {
			document.querySelector('.centeractive').classList.remove('centeractive')
			document.querySelector(`[data-page="1"] a`).classList.add('centeractive')
		})
		document.querySelector('[data-page="2"]').addEventListener('click',() => {
			document.querySelector('.centeractive').classList.remove('centeractive')
			document.querySelector(`[data-page="2"] a`).classList.add('centeractive')
		})
	}else if(totalPage === 3){
		pagination.innerHTML = pagination.innerHTML+`<li data-page="0"><a href="#" class="le">上一页</a></li>`
		pagination.innerHTML = pagination.innerHTML+`<li data-page="1"><a href="#" class="li2 centeractive">1</a></li>`
		pagination.innerHTML = pagination.innerHTML+`<li data-page="2"><a href="#" class="li2">2</a></li>`
		pagination.innerHTML = pagination.innerHTML+`<li data-page="3"><a href="#" class="li2">3</a></li>`
		pagination.innerHTML = pagination.innerHTML+`<li data-page="100"><a href="#" class="ri">下一页</a></li>`
		document.querySelector('.center').classList.add('cente')
		document.querySelector('.cente').classList.remove('center')
		document.querySelector('[data-page="0"]').addEventListener('click',() => {
			let thispg = document.querySelector('.centeractive').closest('LI').dataset.page
			if(thispg > 1)
			{
				thispg--
				document.querySelector('.centeractive').classList.remove('centeractive')
			}
			document.querySelector(`[data-page="${thispg}"] a`).classList.add('centeractive')
		})
		document.querySelector('[data-page="100"]').addEventListener('click',() => {
			let thispg = document.querySelector('.centeractive').closest('LI').dataset.page
			if(thispg < totalPage)
			{
				thispg++
				document.querySelector('.centeractive').classList.remove('centeractive')
			}
			document.querySelector(`[data-page="${thispg}"] a`).classList.add('centeractive')
		})
		document.querySelector('[data-page="1"]').addEventListener('click',() => {
			document.querySelector('.centeractive').classList.remove('centeractive')
			document.querySelector(`[data-page="1"] a`).classList.add('centeractive')
		})
		document.querySelector('[data-page="2"]').addEventListener('click',() => {
			document.querySelector('.centeractive').classList.remove('centeractive')
			document.querySelector(`[data-page="2"] a`).classList.add('centeractive')
		})
		document.querySelector('[data-page="3"]').addEventListener('click',() => {
			document.querySelector('.centeractive').classList.remove('centeractive')
			document.querySelector(`[data-page="3"] a`).classList.add('centeractive')
		})
	}else {
		for(let i = 0;i <= totalPage+1;i++)
		{
				if(i === 0 )
				{
					pagination.innerHTML = pagination.innerHTML+`<li data-page="${i}"><a href="#" class="le">上一页</a></li>`
				}else if(i === (totalPage+1))
				{
					pagination.innerHTML = pagination.innerHTML+`<li data-page="100"><a href="#" class="ri">下一页</a></li>`
				}else if(i === 1)
				{
					pagination.innerHTML = pagination.innerHTML+`<li data-page="${i}"><a href="#" class="li2 centeractive">${i}</a></li>`
				}else  {
					pagination.innerHTML = pagination.innerHTML+`<li data-page="${i}"><a href="#" class="li2">${i}</a></li>`
					if(i>4)
					{
						document.querySelector(`[data-page="${i}"]`).style.display = 'none'
					}
				}
		}
		let thispages = 1
		document.querySelector('[data-page="0"]').addEventListener('click',() => {
				if(thispages > 1)
				{
					thispages--;
				}
				page(thispages)
				if(thispages === 1)
				{
					for(let i = 1 ; i <= totalPage ; i++)
						{
							document.querySelector(`[data-page="${i}"]`).style.display = "none"
						}
						document.querySelector('.centeractive').classList.remove('centeractive')
						document.querySelector(`[data-page="${thispages}"]`).style.display = "block"
						document.querySelector(`[data-page="${thispages}"] a`).classList.add('centeractive')
						document.querySelector(`[data-page="${thispages+1}"]`).style.display = "block"
						document.querySelector(`[data-page="${thispages+2}"]`).style.display = "block"
						document.querySelector(`[data-page="${thispages+3}"]`).style.display = "block"
				}else if(thispages === 2){
					for(let i = 1 ; i <= totalPage ; i++)
					{
						document.querySelector(`[data-page="${i}"]`).style.display = "none"
					}
					document.querySelector('.centeractive').classList.remove('centeractive')
					document.querySelector(`[data-page="${thispages-1}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages}"] a`).classList.add('centeractive')
					document.querySelector(`[data-page="${thispages}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages+1}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages+2}"]`).style.display = "block"
				}else if(thispages === 3){
					for(let i = 1 ; i <= totalPage ; i++)
						{
							document.querySelector(`[data-page="${i}"]`).style.display = "none"
						}
						document.querySelector('.centeractive').classList.remove('centeractive')
						document.querySelector(`[data-page="${thispages-2}"]`).style.display = "block"
						document.querySelector(`[data-page="${thispages}"] a`).classList.add('centeractive')
						document.querySelector(`[data-page="${thispages-1}"]`).style.display = "block"
						document.querySelector(`[data-page="${thispages}"]`).style.display = "block"
						document.querySelector(`[data-page="${thispages+1}"]`).style.display = "block"
				}else {
					for(let i = 1 ; i <= totalPage ; i++)
					{
						document.querySelector(`[data-page="${i}"]`).style.display = "none"
					}
					document.querySelector('.centeractive').classList.remove('centeractive')
					document.querySelector(`[data-page="${thispages-2}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages}"] a`).classList.add('centeractive')
					document.querySelector(`[data-page="${thispages-1}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages+1}"]`).style.display = "block"
				}
		})
		document.querySelector('[data-page="100"]').addEventListener('click',() => {
			if(thispages < totalPage)
				{
					thispages++;
				}
				if(thispages === totalPage)
				{
					for(let i = 1 ; i <= totalPage ; i++)
							{
								document.querySelector(`[data-page="${i}"]`).style.display = "none"
							}
							document.querySelector('.centeractive').classList.remove('centeractive')
							document.querySelector(`[data-page="${totalPage-3}"]`).style.display = "block"
							document.querySelector(`[data-page="${totalPage}"] a`).classList.add('centeractive')
							document.querySelector(`[data-page="${totalPage-2}"]`).style.display = "block"
							document.querySelector(`[data-page="${totalPage-1}"]`).style.display = "block"
							document.querySelector(`[data-page="${totalPage}"]`).style.display = "block"
				}else if(thispages === 2){
					for(let i = 1 ; i <= totalPage ; i++)
					{
						document.querySelector(`[data-page="${i}"]`).style.display = "none"
					}
					document.querySelector('.centeractive').classList.remove('centeractive')
					document.querySelector(`[data-page="${thispages-1}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages}"] a`).classList.add('centeractive')
					document.querySelector(`[data-page="${thispages}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages+1}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages+2}"]`).style.display = "block"
				}else if(thispages === 3){
					for(let i = 1 ; i <= totalPage ; i++)
						{
							document.querySelector(`[data-page="${i}"]`).style.display = "none"
						}
						document.querySelector('.centeractive').classList.remove('centeractive')
						document.querySelector(`[data-page="${thispages-2}"]`).style.display = "block"
						document.querySelector(`[data-page="${thispages}"] a`).classList.add('centeractive')
						document.querySelector(`[data-page="${thispages-1}"]`).style.display = "block"
						document.querySelector(`[data-page="${thispages}"]`).style.display = "block"
						document.querySelector(`[data-page="${thispages+1}"]`).style.display = "block"
				}else {
					for(let i = 1 ; i <= totalPage ; i++)
					{
						document.querySelector(`[data-page="${i}"]`).style.display = "none"
					}
					document.querySelector('.centeractive').classList.remove('centeractive')
					document.querySelector(`[data-page="${thispages-2}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages}"] a`).classList.add('centeractive')
					document.querySelector(`[data-page="${thispages-1}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages}"]`).style.display = "block"
					document.querySelector(`[data-page="${thispages+1}"]`).style.display = "block"
				}
		})
			for(let k = 1 ; k <= totalPage ; k++)
			{
					if(k === 1)
					{
						document.querySelector(`[data-page="${k}"]`).addEventListener('click' , () => {
							for(let i = 1 ; i <= totalPage ; i++)
							{
								document.querySelector(`[data-page="${i}"]`).style.display = "none"
							}
							document.querySelector('.centeractive').classList.remove('centeractive')
							document.querySelector(`[data-page="${k}"]`).style.display = "block"
							document.querySelector(`[data-page="${k}"] a`).classList.add('centeractive')
							document.querySelector(`[data-page="${k+1}"]`).style.display = "block"
							document.querySelector(`[data-page="${k+2}"]`).style.display = "block"
							document.querySelector(`[data-page="${k+3}"]`).style.display = "block"
							thispages = k
						})
					}else if(k === 2)
					{
						document.querySelector(`[data-page="${k}"]`).addEventListener('click' , () => {
							for(let i = 1 ; i <= totalPage ; i++)
							{
								document.querySelector(`[data-page="${i}"]`).style.display = "none"
							}
							document.querySelector('.centeractive').classList.remove('centeractive')
							document.querySelector(`[data-page="${k-1}"]`).style.display = "block"
							document.querySelector(`[data-page="${k}"] a`).classList.add('centeractive')
							document.querySelector(`[data-page="${k}"]`).style.display = "block"
							document.querySelector(`[data-page="${k+1}"]`).style.display = "block"
							document.querySelector(`[data-page="${k+2}"]`).style.display = "block"
							thispages = k
						})
					}else if(k === 3)
					{
						document.querySelector(`[data-page="${k}"]`).addEventListener('click' , () => {
							for(let i = 1 ; i <= totalPage ; i++)
							{
								document.querySelector(`[data-page="${i}"]`).style.display = "none"
							}
							document.querySelector('.centeractive').classList.remove('centeractive')
							document.querySelector(`[data-page="${k-2}"]`).style.display = "block"
							document.querySelector(`[data-page="${k}"] a`).classList.add('centeractive')
							document.querySelector(`[data-page="${k-1}"]`).style.display = "block"
							document.querySelector(`[data-page="${k}"]`).style.display = "block"
							document.querySelector(`[data-page="${k+1}"]`).style.display = "block"
							thispages = k
						})
					}else if(k === totalPage)
					{
						document.querySelector(`[data-page="${k}"]`).addEventListener('click' , () => {
							for(let i = 1 ; i <= totalPage ; i++)
							{
								document.querySelector(`[data-page="${i}"]`).style.display = "none"
							}
							document.querySelector('.centeractive').classList.remove('centeractive')
							document.querySelector(`[data-page="${totalPage-3}"]`).style.display = "block"
							document.querySelector(`[data-page="${k}"] a`).classList.add('centeractive')
							document.querySelector(`[data-page="${totalPage-2}"]`).style.display = "block"
							document.querySelector(`[data-page="${totalPage-1}"]`).style.display = "block"
							document.querySelector(`[data-page="${totalPage}"]`).style.display = "block"
							thispages = k
						})
					}else
					{
						document.querySelector(`[data-page="${k}"]`).addEventListener('click' , () => {
							for(let i = 1 ; i <= totalPage ; i++)
							{
								document.querySelector(`[data-page="${i}"]`).style.display = "none"
							}
							document.querySelector('.centeractive').classList.remove('centeractive')
							document.querySelector(`[data-page="${k-2}"]`).style.display = "block"
							document.querySelector(`[data-page="${k}"] a`).classList.add('centeractive')
							document.querySelector(`[data-page="${k-1}"]`).style.display = "block"
							document.querySelector(`[data-page="${k}"]`).style.display = "block"
							document.querySelector(`[data-page="${k+1}"]`).style.display = "block"
							thispages = k
						})
					}
			}
	}
}

