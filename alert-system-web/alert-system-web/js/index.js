let dayTime = ''

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
	dayTime = now.getFullYear()+'-'+('0' + (now.getMonth() + 1)).slice(-2)+'-'+('0' + now.getDate()).slice(-2)
	document.querySelector('.time').innerHTML = formattedTime
	setTimeout(getNowTime, 1000);
}
getNowTime()
console.log(dayTime);

//确定
document.querySelector('.bosure').addEventListener('click', function () {
	document.querySelector('.loader-2').style.display = 'block'
	document.querySelector('.loading').style.display = 'block'
	setInterval(function () {
		document.querySelector('.loader-2').style.display = 'none'
		document.querySelector('.loading').style.display = 'none'
		const alarm = document.querySelector('.alarmImg')
		alarm?.classList.remove('alarmImg')
		alarm?.classList.add('alarmImgs')
		document.querySelector('.blackbody').style.display = 'none'
		document.querySelector('.success').style.display = 'block'
		setInterval(function() {
			document.querySelector('.success').style.display = 'none'
		},1500)
	}, 3000);
})

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

//退出登录
document.querySelector('.exit').addEventListener('click', () => {
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

//总条数
myAxios({
	url: baseURL + '/alarm/selectAllRealtimeAlarmOrderedByOccurTime',
	method: 'POST'
}).then(result => {
	console.log(result);
	forpage(result.data.length)
	// forpage(30)
	document.querySelector('.xj').innerHTML = `共${result.data.length}条`
}).catch(error => {
	console.dir(error)
})

//获取username
function getQueryParams() {
	myAxios({
		url: baseURL + '/user/getInfo'
	}).then(result => {
		console.log(result.data);
		document.querySelector('.u').innerHTML = `当前用户：${result.data.username}`
		// document.querySelector('.userImg1').src = result.data.photo
		console.log(document.querySelector('.userImg1').src);
	}).catch(error => {
		console.log(error);
	})
}
getQueryParams();

//分页器(有限制的实时报警)
function page(page) {
	const pageSize = 30
	// const totalPage = parseInt((total % pageSize == 0) ? (total / pageSize) : (total / pageSize + 1))
	let items = ''
	let pageNum = 1
	// console.log(pageNum);

	//实时警报展示
	myAxios({
		url: baseURL + '/alarm/selectAllRealtimeAlarmOrderedByOccurTimePaged',
		method: 'POST',
		data: {
			pageNum,
			pageSize
		}
	}).then(result => {
		console.log(result);
		console.log(result.data.total);
		// forpage(result.data.total)
		// document.querySelector('.xj').innerHTML = `共${result.data.total}条`
		const datas = result.data.records
		for (let i = 1; i <= datas.length; i++) {
			if (datas[i - 1].confirmStatus) {
				datas[i - 1].confirmStatus = '已确认'
			} else {
				datas[i - 1].confirmStatus = '未确认'
			}
			if (datas[i - 1].recoverStatus) {
				datas[i - 1].recoverStatus = '已恢复'
			} else {
				datas[i - 1].recoverStatus = '未恢复'
			}
			if(i % 2 === 0)
			{
				if (datas[i - 1].type.length <= 4) {
					if (datas[i - 1].type === '压力异常') {
						items = items +
							` <p class="partpss">
						<span class="span1">${datas[i - 1].source}</span>
						<span class="span2">${datas[i - 1].occurTime}</span>
						<span class="spanss3">${datas[i - 1].type}</span>
						<span class="span6">${datas[i - 1].confirmStatus}&nbsp;${datas[i - 1].recoverStatus}</span>			
				</p>`
					} else if (datas[i - 1].type === '湿度过高') {
						items = items +
							` <p class="partpss">
						<span class="span1">${datas[i - 1].source}</span>
						<span class="span2">${datas[i - 1].occurTime}</span>
						<span class="spansp13">${datas[i - 1].type}</span>
						<span class="span6">${datas[i - 1].confirmStatus}&nbsp;${datas[i - 1].recoverStatus}</span>			
				</p>`
					}else if (datas[i - 1].type === '温度过高') {
						items = items +
							` <p class="partpss">
						<span class="span1">${datas[i - 1].source}</span>
						<span class="span2">${datas[i - 1].occurTime}</span>
						<span class="spansp23">${datas[i - 1].type}</span>
						<span class="span6">${datas[i - 1].confirmStatus}&nbsp;${datas[i - 1].recoverStatus}</span>			
				</p>`
					}else if (datas[i - 1].type === '传感器失效') {
						items = items +
							` <p class="partpss">
						<span class="span1">${datas[i - 1].source}</span>
						<span class="span2">${datas[i - 1].occurTime}</span>
						<span class="spansp33">${datas[i - 1].type}</span>
						<span class="span6">${datas[i - 1].confirmStatus}&nbsp;${datas[i - 1].recoverStatus}</span>			
				</p>`
					} else if (datas[i - 1].type === '设备故障') {
						items = items +
							` <p class="partpss">
						<span class="span1">${datas[i - 1].source}</span>
						<span class="span2">${datas[i - 1].occurTime}</span>
						<span class="spanss43">${datas[i - 1].type}</span>
						<span class="span6">${datas[i - 1].confirmStatus}&nbsp;${datas[i - 1].recoverStatus}</span>			
				</p>`
					}else{
						items = items +
							` <p class="partpss">
						<span class="span1">${datas[i - 1].source}</span>
						<span class="span2">${datas[i - 1].occurTime}</span>
						<span class="spanssp3">${datas[i - 1].type}</span>
						<span class="span6">${datas[i - 1].confirmStatus}&nbsp;${datas[i - 1].recoverStatus}</span>	
			</p>`
					}
				} else {
					if (datas[i - 1].type === '电源电压不稳定') {
						items = items +
							` <p class="partpss">
						<span class="span1">${datas[i - 1].source}</span>
						<span class="span2">${datas[i - 1].occurTime}</span>
						<span class="spanps3">${datas[i - 1].type}</span>
						<span class="span6">${datas[i - 1].confirmStatus}&nbsp;${datas[i - 1].recoverStatus}</span>			
				</p>`
					} else {
						items = items +
							` <p class="partpss">
						<span class="span1">${datas[i - 1].source}</span>
						<span class="span2">${datas[i - 1].occurTime}</span>
						<span class="spanssp3">${datas[i - 1].type}</span>
						<span class="span6">${datas[i - 1].confirmStatus}&nbsp;${datas[i - 1].recoverStatus}</span>	
			</p>`
					}
					
				}
			}else {
				if (datas[i - 1].type.length <= 4) {
					if (datas[i - 1].type === '压力异常') {
						items = items +
							` <p class="partps">
						<span class="span1">${datas[i - 1].source}</span>
						<span class="span2">${datas[i - 1].occurTime}</span>
						<span class="spanss3">${datas[i - 1].type}</span>
						<span class="span6">${datas[i - 1].confirmStatus}&nbsp;${datas[i - 1].recoverStatus}</span>			
				</p>`
					} else if (datas[i - 1].type === '湿度过高') {
						items = items +
							` <p class="partps">
						<span class="span1">${datas[i - 1].source}</span>
						<span class="span2">${datas[i - 1].occurTime}</span>
						<span class="spansp13">${datas[i - 1].type}</span>
						<span class="span6">${datas[i - 1].confirmStatus}&nbsp;${datas[i - 1].recoverStatus}</span>			
				</p>`
					}else if (datas[i - 1].type === '温度过高') {
						items = items +
							` <p class="partps">
						<span class="span1">${datas[i - 1].source}</span>
						<span class="span2">${datas[i - 1].occurTime}</span>
						<span class="spansp23">${datas[i - 1].type}</span>
						<span class="span6">${datas[i - 1].confirmStatus}&nbsp;${datas[i - 1].recoverStatus}</span>			
				</p>`
					}else if (datas[i - 1].type === '传感器失效') {
						items = items +
							` <p class="partps">
						<span class="span1">${datas[i - 1].source}</span>
						<span class="span2">${datas[i - 1].occurTime}</span>
						<span class="spansp33">${datas[i - 1].type}</span>
						<span class="span6">${datas[i - 1].confirmStatus}&nbsp;${datas[i - 1].recoverStatus}</span>			
				</p>`
					} else if (datas[i - 1].type === '设备故障') {
						items = items +
							` <p class="partps">
						<span class="span1">${datas[i - 1].source}</span>
						<span class="span2">${datas[i - 1].occurTime}</span>
						<span class="spanss43">${datas[i - 1].type}</span>
						<span class="span6">${datas[i - 1].confirmStatus}&nbsp;${datas[i - 1].recoverStatus}</span>			
				</p>`
					}else{
						items = items +
							` <p class="partps">
						<span class="span1">${datas[i - 1].source}</span>
						<span class="span2">${datas[i - 1].occurTime}</span>
						<span class="spanssp3">${datas[i - 1].type}</span>
						<span class="span6">${datas[i - 1].confirmStatus}&nbsp;${datas[i - 1].recoverStatus}</span>	
			</p>`
					}
				} else {
					if (datas[i - 1].type === '电源电压不稳定') {
						items = items +
							` <p class="partps">
						<span class="span1">${datas[i - 1].source}</span>
						<span class="span2">${datas[i - 1].occurTime}</span>
						<span class="spanps3">${datas[i - 1].type}</span>
						<span class="span6">${datas[i - 1].confirmStatus}&nbsp;${datas[i - 1].recoverStatus}</span>			
				</p>`
					} else {
						items = items +
							` <p class="partps">
						<span class="span1">${datas[i - 1].source}</span>
						<span class="span2">${datas[i - 1].occurTime}</span>
						<span class="spanssp3">${datas[i - 1].type}</span>
						<span class="span6">${datas[i - 1].confirmStatus}&nbsp;${datas[i - 1].recoverStatus}</span>	
			</p>`
					}
					
				}
			}
		}
		document.querySelector('.partsP').innerHTML = items
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

//时间tab栏切换
function tabtime() {
	// document.querySelector('#id1').style.display = "none"
	document.querySelector('#id2').style.display = "none"
	document.querySelector('#id5').style.display = "none"
	document.querySelector('#time1').addEventListener('click', () => {
		console.log("1");
		document.querySelector('#id1').style.display = "block"
		document.querySelector('#id2').style.display = "none"
		document.querySelector('#id5').style.display = "none"
		document.querySelector('.timeactive').classList.remove('timeactive')
		document.querySelector('#time1').classList.add('timeactive')
	})
	document.querySelector('#time2').addEventListener('click', () => {
		console.log("1");
		document.querySelector('#id1').style.display = "none"
		document.querySelector('#id2').style.display = "block"
		document.querySelector('#id5').style.display = "none"
		document.querySelector('.timeactive').classList.remove('timeactive')
		document.querySelector('#time2').classList.add('timeactive')
	})
	document.querySelector('#time4').addEventListener('click', () => {
		console.log("1");
		document.querySelector('#id1').style.display = "none"
		document.querySelector('#id2').style.display = "none"
		document.querySelector('#id5').style.display = "block"
		document.querySelector('.timeactive').classList.remove('timeactive')
		document.querySelector('#time4').classList.add('timeactive')
	})
}
tabtime()


//近七天发生警报可视柱状图
function dayseven(datasdata,datalength) {
	let dataweekdata = []
	for(let i = 1 ; i <= datalength ;i++)
	{
		dataweekdata[i-1] = datasdata[i-1].count
	}
	let dataweek = []
	if(datalength === 1)
	{
		dataweek = ['星期日']
	}else if(datalength === 2)
	{
		dataweek = ['星期日','星期一']
	}else if(datalength === 3)
	{
		dataweek = ['星期日','星期一','星期二']
	}else if(datalength === 4)
	{
		dataweek = ['星期日','星期一','星期二','星期三']
	}else if(datalength === 5)
	{
		dataweek = ['星期日','星期一','星期二','星期三','星期四']
	}else if(datalength === 6)
	{
		dataweek = ['星期日','星期一','星期二','星期三','星期四','星期五']
	}else if(datalength === 7)
	{
		dataweek = ['星期日','星期一','星期二','星期三','星期四','星期五','星期六']
	}
	console.log(dataweek);
	let element = document.querySelector('#id1');
	if (element) {
		let myChart = echarts.init(element);
		let option = {
			title: {
				// text: '销售数据对比'
			},
			tooltip: {
				trigger: 'axis'
			},
			legend: {
				data: ['一周警报数（柱状图）', '一周警报数（折线图）']
			},
			grid: {
				left: '16%',
				right: '14%',
				bottom: '3%',
				containLabel: true
			},
			xAxis: {
				type: 'category',
				boundaryGap: false,
				data:dataweek
			},
			yAxis: [
				{
					type: 'value',
					name: '一周警报数',
					position: 'left'
				},
				{
					type: 'value',
					// name: '一周警报数',
					position: 'right'
				}
			],
			series: [
				{
					name: '一周警报数（柱状图）',
					type: 'bar',
					data: dataweekdata,
					itemStyle: {
						color: '#8fd766'
					}
				},
				{
					name: '一周警报数（折线图）',
					type: 'line',
					yAxisIndex: 1,
					data: dataweekdata,
					itemStyle: {
						color: '#f15023'
					}
				}
			]
		};

		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);

	} else {
		console.log('找不到 #myChart 元素');
	}	
}
//获取统计图的数据信息通过时间
function databytime(time) {
	myAxios({
		url:baseURL+'/alarm/selectAlarmNumsByTime',
		method:'POST',
		data:{
			time_type:time
		}
	}).then(result => {
		const datasdata = result.data
		const datalength = result.data.length
		if(time === 'week')
		dayseven(datasdata,datalength)
		if(time === 'month')
		id2(datasdata,datalength)
		if(time === 'year')
		id5(datasdata,datalength)
	}).catch(error => {
		console.dir(error)
	})
}
databytime("week")
databytime("month")
databytime("year")

//每一月数据
function id2(datasdata,datalength) {
	let datamonthdata = []
	for(let i = 1 ; i <= datalength ;i++)
	{
		datamonthdata[i-1] = datasdata[i-1].count
	}
	let datamonth = []
	if(datalength === 1)
	{
		datamonth = ['第一周']
	}else if(datalength === 2)
	{
		datamonth = ['第一周','第二周']
	}else if(datalength === 3)
	{
		datamonth = ['第一周','第二周','第三周']
	}else if(datalength === 4)
	{
		datamonth = ['第一周','第二周','第三周','第四周']
	}else if(datalength === 5)
	{
		datamonth = ['第一周','第二周','第三周','第四周','第五周']
	}
	let id2 = document.querySelector('#id2');
	if (id2) {
		let myChart = echarts.init(id2);
		let option = {
			title: {
				// text: '销售数据对比'
			},
			tooltip: {
				trigger: 'axis'
			},
			legend: {
				data: ['一月警报数（柱状图）', '一月警报数（折线图）']
			},
			grid: {
				left: '16%',
				right: '14%',
				bottom: '3%',
				containLabel: true
			},
			xAxis: {
				type: 'category',
				boundaryGap: false,
				data: datamonth
			},
			yAxis: [
				{
					type: 'value',
					name: '一月警报数',
					position: 'left'
				},
				{
					type: 'value',
					name: '一月警报数',
					position: 'right',
					show: false
				}
			],
			series: [
				{
					name: '一月警报数（柱状图）',
					type: 'bar',
					data: datamonthdata,
					itemStyle: {
						color: '#b8deeb'
					}
				},
				{
					name: '一月警报数（折线图）',
					type: 'line',
					yAxisIndex: 1,
					data: datamonthdata,
					itemStyle: {
						color: '#f15023'
					}
				}
			]
		};

		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);

	} else {
		console.log('找不到 #myChart 元素');
	}
}

//每一年数据
function id5(datasdata,datalength) {
	let datayeardata = []
	for(let i = 1 ; i <= datalength ;i++)
	{
		datayeardata[i-1] = datasdata[i-1].count
	}
	let datayear = []
	if(datalength === 1)
	{
		datayear = ['1月']
	}else if(datalength === 2)
	{
		datayear = ['1月','2月']
	}else if(datalength === 3)
	{
		datayear = ['1月','2月','3月']
	}else if(datalength === 4)
	{
		datayear = ['1月','2月','3月','4月']
	}else if(datalength === 5)
	{
		datayear = ['1月','2月','3月','4月','5月']
	}else if(datalength === 6)
	{
		datayear = ['1月','2月','3月','4月','5月','6月']
	}else if(datalength === 7)
	{
		datayear = ['1月','2月','3月','4月','5月','6月','7月']
	}else if(datalength === 8)
	{
		datayear = ['1月','2月','3月','4月','5月','6月','7月','8月']
	}else if(datalength === 9)
	{
		datayear = ['1月','2月','3月','4月','5月','6月','7月','8月','9月']
	}else if(datalength === 10)
	{
		datayear = ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月']
	}else if(datalength === 11)
	{
		datayear = ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月']
	}else if(datalength === 12)
	{
		datayear = ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
	}
	let id5 = document.querySelector('#id5');
	if (id5) {
		let myChart = echarts.init(id5);
		let option = {
			title: {
				text: '近一年警报数量'
			},
			tooltip: {},
			grid: {
				left: '16%',
				right: '14%',
				bottom: '3%',
				containLabel: true
			},
			xAxis: {
				data: datayear
			},
			yAxis: {},
			series: [
				{
					name: '近一年警报数量',
					type: 'line',
					data: datayeardata,
					itemStyle: {
						color: '#f15023'
					}
				}
			]
		};
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);

	} else {
		console.log('找不到 #myChart 元素');
	}
}

let data1 = 0
let data2 = 0
let data3 = 0
let data4 = 0
let data5 = 0
let data6 = 0
let data7 = 0
	function id3data(typedata,datas)



	
	{
		if(typedata === '电源电压不稳定') data1 = datas
		if(typedata === '压力异常') data2 = datas
		if(typedata === '湿度过高') data3 = datas
		if(typedata === '温度过高') data4 = datas
		if(typedata === '传感器失效') data5 = datas
		if(typedata === '设备故障') data6 = datas
		if(typedata === '其他') data7 = datas
	}

//数据获取type
function databytype(typedata) {
  return new Promise((resolve, reject) => {
    myAxios({
      url: baseURL + '/alarm/selectAlarmNumsByType',
      method: 'POST',
      data: {
        type: typedata
      }
    }).then(result => {
      id3data(typedata, result.data)
      resolve(result.data)
    }).catch(error => {
      console.dir(error)
      reject(error)
    })
  })
}
Promise.all([
  databytype('电源电压不稳定'),
  databytype('压力异常'),
  databytype('湿度过高'),
  databytype('温度过高'),
  databytype('传感器失效'),
  databytype('设备故障'),
  databytype('其他')
]).then(() => {
  console.log(data1, data2, data3, data4, data5, data6, data7)
	id3()
}).catch(error => {
  console.dir(error)
})

// 指定图表的配置项和数据
function id3() {
	let elements = document.querySelector('#id3');
	if (elements) {
		let myCharts = echarts.init(elements);
		// 配置选项
		var option = {

			tooltip: {
				trigger: 'item'
			},
			legend: {
				orient: 'ertical',
				left: 'left'
			},
			series: [
				{
					name: '警报源占比',
					type: 'pie',
					radius: '50%',
					data: [
						{ value: data1, name: '电源电压不稳定', itemStyle: { color: '#f7d260' } },
						{ value: data2, name: '压力异常', itemStyle: { color: '#c54640' } },
						{ value: data3, name: '湿度过高', itemStyle: { color: '#94d763' } },
						{ value: data4, name: '温度过高', itemStyle: { color: '#69d2e5' } },
						{ value: data5, name: '传感器失效', itemStyle: { color: '#a8cdf9' } },
						{ value: data6, name: '设备故障', itemStyle: { color: '#4172f5' } },
						{ value: data7, name: '其他', itemStyle: { color: '#7a979c' } }
					],
					emphasis: {
						itemStyle: {
							shadowBlur: 10,
							shadowOffsetX: 0,
							shadowColor: 'rgba(68, 239, 248, 0.5)'
						}
					}
				}
			]
		};

		// 使用配置项显示图表
		myCharts.setOption(option);
	} else {
		console.log('找不到 #myChart 元素');
	}
}

//关闭按钮
function close() {
	document.querySelector('.close').addEventListener('mouseenter', () => {
		document.querySelector('#closeimg').src = './img/close2.png'
		document.querySelector('.close').addEventListener('click', () => {
			document.querySelector('.Pop-ups').style.display = 'none'
		})
	})
	document.querySelector('.close').addEventListener('mouseleave', () => {
		document.querySelector('#closeimg').src = './img/close.png'
		document.querySelector('.close').addEventListener('click', () => {
			document.querySelector('.Pop-ups').style.display = 'none'
		})
	})
	document.querySelector('#closeimg').addEventListener('click', () => {
		document.querySelector('.Pop-ups').style.display = 'none'
	})
}
close()

//打开弹窗
function openpop() {
	document.querySelector('.popbutton').addEventListener('click', () => {
		document.querySelector('.Pop-ups').style.display = 'block'
	})
}
openpop()

//首页轮播内容补充
function contentindex()
{
	myAxios({
		url:baseURL+'/alarm/selectRealtimeAlarmOrdered',
		method:'POST',
	}).then(result => {
		for(let i = 0 ; i < result.data.length ; i++)
		{
			if (result.data[i].confirmStatus) {
				result.data[i].confirmStatus = '已确认'
			} else {
				result.data[i].confirmStatus = '未确认'
			}
			if (result.data[i].recoverStatus) {
				result.data[i].recoverStatus = '已恢复'
			} else {
				result.data[i].recoverStatus = '未恢复'
			}
			if(i === 0) 
			{
				document.querySelector('.carousel').innerHTML = `<li class="carousel-item active">
				实时警报${i+1}&ensp;${result.data[i].occurTime}&ensp;&ensp;${result.data[i].confirmStatus}&ensp;${result.data[i].recoverStatus}&ensp;${result.data[i].type}</li>`
			}else {
				document.querySelector('.carousel').innerHTML =document.querySelector('.carousel').innerHTML +`<li class="carousel-item">
			实时警报${i+1}&ensp;${result.data[i].occurTime}&ensp;&ensp;${result.data[i].confirmStatus}&ensp;${result.data[i].recoverStatus}&ensp;${result.data[i].type}</li>`
			}
			carousels()
		}
	}).catch(error => {
		console.dir(error)
	})
}
contentindex()


			function carousels()
			{
				const carouselItems = document.querySelectorAll('.carousel-item');
			let currentIndex = 0;
			function showCarouselItem(index) {
				carouselItems.forEach((item, i) => {
					if (i === index) {
						item.classList.add('active');
					} else {
						item.classList.remove('active');
					}
				});
			}
			
			function nextCarouselItem() {
				currentIndex++;
				if (currentIndex >= carouselItems.length) {
					currentIndex = 0;
				}
				showCarouselItem(currentIndex);
			}
			
			setInterval(nextCarouselItem, 1800);
			}
			
			

//十条紧急处理的实时警报
function emergency() {
	myAxios({
		url:baseURL+'/alarm/selectRealtimeAlarmOrdered',
		method:'POST'
	}).then(result => {
		console.log(result.data);
		let item = ''
		for(let i = 1 ; i <= result.data.length ; i++)
		{
			if (result.data[i - 1].confirmStatus) {
				result.data[i - 1].confirmStatus = '已确认'
			} else {
				result.data[i - 1].confirmStatus = '未确认'
			}
			if (result.data[i - 1].recoverStatus) {
				result.data[i - 1].recoverStatus = '已恢复'
			} else {
				result.data[i - 1].recoverStatus = '未恢复'
			}
				item = item + `<div class='divclass'>
													<div class='div1'>${result.data[i-1].source}</div>
													<div class='div2'>${result.data[i-1].type}</div>
													<div class='div3'>${result.data[i-1].occurTime}</div>
													<div class='div4'>${result.data[i-1].level}级</div>
													<div class='div5'>${result.data[i-1].confirmStatus}</div>
													<div class='div6'>${result.data[i-1].recoverStatus}</div>
											</div>`
		}
		document.querySelector('.poppartp').innerHTML = item
	}).catch(error => {
		console.dir(error);
	})
}
emergency()

//获取首页今日产生警报数
function allindex()
{
	myAxios({
		url:baseURL+'/alarm/selectAlarmNumsOneDay',
		method:'POST',
		data:{
			time:dayTime
		}
	}).then(result => {
		console.log(result.data);
		if(result.data === 0) 
		{
			document.querySelector('#alltx').innerHTML = 0
		}else {
			document.querySelector('#alltx').innerHTML = result.data
		}
		
	}).catch(error => {
		console.dir(error)
	})
}
allindex()

//获取首页今日产生历史警报数
function hisindex()
{
	myAxios({
		url:baseURL+'/alarm/selectHistoricalAlarmNumsOneDay',
		method:'POST',
		data:{
			time:dayTime
		}
	}).then(result => {
		if(result.data === 0) 
		{
			document.querySelector('#histx').innerHTML = 0
		}else {
			document.querySelector('#histx').innerHTML = result.data
		}
	}).catch(error => {
		console.dir(error)
	})
}
hisindex()

//获取首页今日产生实时警报数
function reaindex()
{
	myAxios({
		url:baseURL+'/alarm/selectRealtimeAlarmNumsOneDay',
		method:'POST',
		data:{
			time:dayTime
		}
	}).then(result => {
		if(result.data === 0) 
		{
			document.querySelector('#reltx').innerHTML = 0
		}else {
			document.querySelector('#reltx').innerHTML = result.data
		}
		// forpage(result.data)
	// document.querySelector('.xj').innerHTML = `共${result.data}条`
	}).catch(error => {
		console.dir(error)
	})
}
reaindex()

//高德地图api
// AMapLoader.load({
// 	key: "f4c318422c7af1c57489abeed431e678", //申请好的Web端开发者 Key，调用 load 时必填
// 	version: "2.0", //指定要加载的 JS API 的版本，缺省时默认为 1.4.15
// })
// 	.then((AMap) => {
// 		const map = new AMap.Map("container");
// 		const marker = new AMap.Marker({
// 		position: [116.39, 39.9], //位置
// 	});
// 	map.add(marker); //添加到地图

// 	const infoWindow = new AMap.InfoWindow({
// 		//创建信息窗体
// 		isCustom: true, //使用自定义窗体
// 		content: "<div>HELLO,AMAP!</div>", //信息窗体的内容可以是任意html片段
// 		offset: new AMap.Pixel(16, -45),
// 	});
// 	const onMarkerClick = function (e) {
// 		infoWindow.open(map, e.target.getPosition()); //打开信息窗体
// 		//e.target就是被点击的Marker
// 	};
// 	marker.on("click", onMarkerClick); //绑定click事件

// 	const lineArr = [
// 		[116.368904, 39.913423],
// 		[116.382122, 39.901176],
// 		[116.387271, 39.912501],
// 		[116.398258, 39.904600]
// 	];
// 	const polyline = new AMap.Polyline({
// 		path: lineArr, //设置线覆盖物路径
// 		strokeColor: "#3366FF", //线颜色
// 		strokeWeight: 5, //线宽
// 		strokeStyle: "solid", //线样式
// 	});
// 	map.add(polyline);
// 	})
// 	.catch((e) => {
// 		console.error(e); //加载错误提示
// 	});

// AMapLoader.load({
//     key: "f4c318422c7af1c57489abeed431e678", // 替换为你自己的 API Key
//     version: "2.0",
// }).then((AMap) => {
//     const map = new AMap.Map("container", {
//         zoom: 18, // 设置缩放级别
//         center: [116.397428, 39.90923], // 设置地图中心点
//     });

//     // 设置只显示地形，不显示标签
//     map.setFeatures(['bg', 'road', 'building']); // bg：背景，road：道路，building：建筑物

//     // 定义多个点的坐标和编号
//     const points = [
//         { id: 1, position: [116.397498, 39.90923], highlighted: false },
//         { id: 2, position: [116.397568, 39.90963], highlighted: false },
//         { id: 3, position: [116.397533, 39.90993], highlighted: false },
//         // 可以添加更多点的坐标和编号
//     ];

//     // 用于存储标记的数组
//     const markers = [];

//     // 创建标记和事件
//     points.forEach((point) => {
//         const marker = new AMap.Marker({
//             position: point.position,
//             icon: point.highlighted ? "https://webapi.amap.com/theme/v1.3/markers/n/mark_b.png" : "https://webapi.amap.com/theme/v1.3/markers/n/mark_r.png", // 使用不同图标表示高亮和非高亮
//             extData: point, // 将点的信息存储到标记中
//         });

//         // 添加点击事件获取点信息
//         marker.on("click", () => {
//             const info = marker.getExtData();
//             console.log(`点击了点 ID: ${info.id}`);
//             alert(`点击了点 ID: ${info.id}`); // 弹出该点的编号
//         });

//         // 将标记添加到地图和数组中
//         map.add(marker);
//         markers.push(marker);
//     });

//     // 定义一个方法，用于设置某个点是否高亮
//     function highlightPoint(id, isHighlighted) {
//         markers.forEach((marker) => {
//             const info = marker.getExtData();
//             if (info.id === id) {
//                 info.highlighted = isHighlighted; // 更新点的高亮状态
//                 marker.setIcon(isHighlighted ? "https://webapi.amap.com/theme/v1.3/markers/n/mark_b.png" : "https://webapi.amap.com/theme/v1.3/markers/n/mark_r.png"); // 更新图标
//             }
//         });
//     }

//     // 定义一个函数调用接口更新高亮状态
//     async function updateHighlightStatus() {
//         try {
//             // 调用接口获取数据，假设返回的数据是包含点的 id 和是否高亮的布尔值
//             const response = await fetch("http://localhost:8080/topic/alarm"); // 替换为实际接口
//             const data = await response.json();

//             // 遍历返回的数据并更新对应标记的高亮状态
//             data.forEach((item) => {
//                 highlightPoint(item.id, item.highlighted);
//             });
//         } catch (error) {
//             console.error("获取高亮状态失败:", error);
//         }
//     }

//     // 示例：每隔 5 秒更新一次点的高亮状态
//     setInterval(updateHighlightStatus, 5000);

// }).catch((e) => {
//     console.error(e);
// });

// AMapLoader.load({
//     key: "f4c318422c7af1c57489abeed431e678", // 替换为你自己的 API Key
//     version: "2.0",
// }).then((AMap) => {
//     const map = new AMap.Map("container", {
//         zoom: 18, // 设置缩放级别
//         center: [116.397428, 39.90923], // 设置地图中心点
//     });

//     // 设置只显示地形，不显示标签
//     map.setFeatures(['bg', 'road', 'building']); // bg：背景，road：道路，building：建筑物

//     // 定义多个点的坐标和编号
//     const points = [
//         { id: 42, position: [116.397498, 39.90923], highlighted: false },
//         { id: 62, position: [116.397568, 39.90963], highlighted: false },
//         { id: 82, position: [116.397533, 39.90993], highlighted: false },
//         // 可以添加更多点的坐标和编号
//     ];

//     // 用于存储标记的数组
//     const markers = [];

//     // 创建标记和事件
//     points.forEach((point) => {
//         const marker = new AMap.Marker({
//             position: point.position,
//             icon: point.highlighted ? "https://webapi.amap.com/theme/v1.3/markers/n/mark_b.png" : "https://webapi.amap.com/theme/v1.3/markers/n/mark_r.png", // 使用不同图标表示高亮和非高亮
//             extData: point, // 将点的信息存储到标记中
//         });

//         // 添加点击事件获取点信息
//         marker.on("click", () => {
//             const info = marker.getExtData();
//             console.log(`点击了点 ID: ${info.id}`);
//             alert(`点击了点 ID: ${info.id}`); // 弹出该点的编号
//         });

//         // 将标记添加到地图和数组中
//         map.add(marker);
//         markers.push(marker);
//     });

//     // 定义一个方法，用于设置某个点是否高亮
//     function highlightPoint(id, isHighlighted) {
// 		console.log('id' + id)
//         markers.forEach((marker) => {
//             const info = marker.getExtData();
//             if (info.id === id) {
//                 info.highlighted = isHighlighted; // 更新点的高亮状态
//                 marker.setIcon(isHighlighted ? "https://webapi.amap.com/theme/v1.3/markers/n/mark_b.png" : "https://webapi.amap.com/theme/v1.3/markers/n/mark_r.png"); // 更新图标
//             }
//         });
//     }

//     // 初始化 WebSocket
//     const socket = new SockJS('http://localhost:8080/gs-guide-websocket'); // 替换为实际的 WebSocket 地址
//     const stompClient = Stomp.over(socket);

//     stompClient.connect({}, (frame) => {
//         console.log('Connected: ' + frame);

//         // 订阅主题
//         stompClient.subscribe('/topic/alarm', (message) => {
//             const alarmParticulars = JSON.parse(message.body);
//             console.log('Received alarm particulars:', alarmParticulars);

// 			// let aid = alarmParticulars.source.match(/\d+/);
//             // 更新高亮状态
//             highlightPoint(alarmParticulars.alarmId, true);
//         });
//     });

// }).catch((e) => {
//     console.error(e);
// });


// AMapLoader.load({
//     key: "f4c318422c7af1c57489abeed431e678",
//     version: "2.0",
// }).then((AMap) => {
//     const map = new AMap.Map("container", {
//         zoom: 18,
//         center: [116.397428, 39.90923],
//     });

//     map.setFeatures(['bg', 'road', 'building']);

//     const points = [
//         { id: 42, position: [116.397498, 39.90923], highlighted: false },
//         { id: 62, position: [116.397568, 39.90963], highlighted: false },
//         { id: 82, position: [116.397533, 39.90993], highlighted: false },
//     ];

//     const markers = [];
//     let popupEnabled = false; // 控制浮窗是否可点击
//     let currentAlarmDetails = null; // 存储当前报警详情

//     points.forEach((point) => {
//         const marker = new AMap.Marker({
//             position: point.position,
//             icon: "https://webapi.amap.com/theme/v1.3/markers/n/mark_r.png",
//             extData: point,
//         });

//         marker.on("click", () => {
//             if (popupEnabled) { // 仅在可点击时触发
//                 const info = marker.getExtData();
//                 console.log(`点击了点 ID: ${info.id}`);

//                 // 如果当前有报警详情，则展示
//                 if (currentAlarmDetails) {
//                     document.getElementById("popupContent").innerHTML = `
//                         <strong>报警源:</strong> ${currentAlarmDetails.source}<br>
//                         <strong>报警类型:</strong> ${currentAlarmDetails.type}<br>
//                         <strong>报警等级:</strong> ${currentAlarmDetails.level}<br>
//                         <strong>报警时间:</strong> ${currentAlarmDetails.occurTime}<br>
//                     `;
//                 }

//                 const popup = document.getElementById("infoPopup");
//                 popup.style.display = "block";
//                 // 不再停止闪烁
//             }
//         });

//         map.add(marker);
//         markers.push(marker);
//     });

//     function highlightPoint(id, alarmDetails) {
//         markers.forEach((marker) => {
//             const info = marker.getExtData();
//             if (info.id === id) {
//                 let flashing = true;
//                 info.flashInterval = setInterval(() => {
//                     marker.setIcon(flashing ? "https://webapi.amap.com/theme/v1.3/markers/n/mark_b.png" : "https://webapi.amap.com/theme/v1.3/markers/n/mark_r.png");
//                     flashing = !flashing;
//                 }, 500);

//                 // 设置浮窗可点击
//                 popupEnabled = true;

//                 // 更新当前报警详情
//                 currentAlarmDetails = alarmDetails;

//                 // 如果需要在浮窗展示警报详细信息
//                 const popup = document.getElementById("infoPopup");
//                 document.getElementById("popupContent").innerHTML = `
//                     <strong>报警源:</strong> ${alarmDetails.source}<br>
//                     <strong>报警类型:</strong> ${alarmDetails.type}<br>
//                     <strong>报警等级:</strong> ${alarmDetails.level}<br>
//                     <strong>报警时间:</strong> ${alarmDetails.occurTime}<br>
//                 `;
//                 popup.style.display = "block"; // 直接显示浮窗
//             }
//         });
//     }

//     const socket = new SockJS('http://localhost:8080/gs-guide-websocket');
//     const stompClient = Stomp.over(socket);

//     stompClient.connect({}, (frame) => {
//         console.log('Connected: ' + frame);
//         stompClient.subscribe('/topic/alarm', (message) => {
//             const alarmParticulars = JSON.parse(message.body);
//             console.log('Received alarm particulars:', alarmParticulars);

//             // 更新高亮状态，并传递报警详细信息
//             highlightPoint(alarmParticulars.alarmId, alarmParticulars);
//         });
//     });

//     // 关闭浮窗逻辑
//     document.getElementById("closePopup").addEventListener("click", () => {
//         document.getElementById("infoPopup").style.display = "none";
//     });

//     document.addEventListener("click", (e) => {
//         const popup = document.getElementById("infoPopup");
//         if (popup.style.display === "block" && !popup.contains(e.target)) {
//             popup.style.display = "none";
//         }
//     });

// }).catch((e) => {
//     console.error(e);
// });

// AMapLoader.load({
//     key: "f4c318422c7af1c57489abeed431e678",
//     version: "2.0",
// }).then((AMap) => {
//     const map = new AMap.Map("container", {
//         zoom: 18,
//         center: [116.397428, 39.90923],
//     });

//     map.setFeatures(['bg', 'road', 'building']);

//     const points = [
//         { id: 42, position: [116.397498, 39.90923] },
//         { id: 62, position: [116.397568, 39.90963] },
//         { id: 82, position: [116.397533, 39.90993] },
//     ];

//     const markers = [];
//     const alarms = []; // 存储所有报警信息

//     points.forEach((point) => {
//         const marker = new AMap.Marker({
//             position: point.position,
//             icon: "https://webapi.amap.com/theme/v1.3/markers/n/mark_r.png",
//             extData: point,
//         });

//         marker.on("mouseover", (e) => {
//             const info = marker.getExtData();
//             const alarmDetails = alarms.find(alarm => alarm.alarmId === info.id); // 查找对应的报警信息

//             if (alarmDetails) {
//                 const popupContent = document.getElementById("popupContent");
//                 popupContent.innerHTML = `
//                     <strong>报警源:</strong> ${alarmDetails.source}<br>
//                     <strong>报警类型:</strong> ${alarmDetails.type}<br>
//                     <strong>报警等级:</strong> ${alarmDetails.level}<br>
//                     <strong>报警时间:</strong> ${alarmDetails.occurTime}<br>
//                 `;

//                 const popup = document.getElementById("infoPopup");
//                 popup.style.display = "block";
//                 popup.style.left = `${e.pixel.x + 10}px`; // 调整弹窗位置
//                 popup.style.top = `${e.pixel.y + 10}px`; // 调整弹窗位置
//             }
//         });

//         marker.on("mouseout", () => {
//             const popup = document.getElementById("infoPopup");
//             popup.style.display = "none"; // 隐藏浮窗
//         });

//         map.add(marker);
//         markers.push(marker);
//     });

//     function highlightPoint(id, alarmDetails) {
//         alarms.push(alarmDetails); // 将新的报警信息存储起来
//         markers.forEach((marker) => {
//             const info = marker.getExtData();
//             if (info.id === id) {
//                 let flashing = true;
//                 info.flashInterval = setInterval(() => {
//                     marker.setIcon(flashing ? "https://webapi.amap.com/theme/v1.3/markers/n/mark_b.png" : "https://webapi.amap.com/theme/v1.3/markers/n/mark_r.png");
//                     flashing = !flashing;
//                 }, 500);
//             }
//         });
//     }

//     const socket = new SockJS('http://localhost:8080/gs-guide-websocket');
//     const stompClient = Stomp.over(socket);

//     stompClient.connect({}, (frame) => {
//         console.log('Connected: ' + frame);
//         stompClient.subscribe('/topic/alarm', (message) => {
//             const alarmParticulars = JSON.parse(message.body);
//             console.log('Received alarm particulars:', alarmParticulars);

//             // 更新高亮状态，并传递报警详细信息
//             highlightPoint(alarmParticulars.alarmId, alarmParticulars);
//         });
//     }, (error) => {
//         console.error('WebSocket error: ', error);
//     });

//     // 关闭浮窗逻辑
//     document.getElementById("closePopup").addEventListener("click", () => {
//         document.getElementById("infoPopup").style.display = "none";
//     });

// }).catch((e) => {
//     console.error(e);
// });


AMapLoader.load({
    key: "f4c318422c7af1c57489abeed431e678",
    version: "2.0",
}).then((AMap) => {
    const map = new AMap.Map("container", {
        zoom: 18,
        center: [116.397428, 39.90923],
    });

    map.setFeatures(['bg', 'road', 'building']);

    const points = [
        { id: 42, position: [116.397498, 39.90923] },
        { id: 62, position: [116.397568, 39.90963] },
        { id: 82, position: [116.397533, 39.90993] },
        { id: 21, position: [116.397098, 39.90923] },
        { id: 41, position: [116.397098, 39.90953] },
        { id: 61, position: [116.397098, 39.90983] },
        { id: 81, position: [116.397098, 39.91013] },
    ];

    const markers = [];
    const alarms = []; // 存储所有报警信息

    points.forEach((point) => {
        const marker = new AMap.Marker({
            position: point.position,
            icon: "https://webapi.amap.com/theme/v1.3/markers/n/mark_r.png",
            extData: point,
        });

        marker.on("mouseover", (e) => {
            const info = marker.getExtData();
            const alarmDetails = alarms.find(alarm => alarm.alarmId === info.id); // 查找对应的报警信息

            if (alarmDetails) {
                const popupContent = document.getElementById("popupContent");
                popupContent.innerHTML = `
                    <strong>报警源:</strong> ${alarmDetails.source}<br>
                    <strong>报警类型:</strong> ${alarmDetails.type}<br>
                    <strong>报警等级:</strong> ${alarmDetails.level}<br>
                    <strong>报警时间:</strong> ${alarmDetails.occurTime}<br>
                `;

                const popup = document.getElementById("infoPopup");
                popup.style.display = "block";

                // 计算浮窗的宽度和高度
                const popupWidth = popup.offsetWidth;
                const popupHeight = popup.offsetHeight;

                // 设置浮窗位置为屏幕中心
                popup.style.left = `${(window.innerWidth - popupWidth) / 2}px`;
                popup.style.top = `${(window.innerHeight - popupHeight) / 2}px`;
            }
        });

        marker.on("mouseout", () => {
            const popup = document.getElementById("infoPopup");
            popup.style.display = "none"; // 隐藏浮窗
        });

        map.add(marker);
        markers.push(marker);
    });

    function highlightPoint(id, alarmDetails) {
        alarms.push(alarmDetails); // 将新的报警信息存储起来
        markers.forEach((marker) => {
            const info = marker.getExtData();
            if (info.id === id) {
                let flashing = true;
                info.flashInterval = setInterval(() => {
                    marker.setIcon(flashing ? "https://webapi.amap.com/theme/v1.3/markers/n/mark_b.png" : "https://webapi.amap.com/theme/v1.3/markers/n/mark_r.png");
                    flashing = !flashing;
                }, 500);
            }
        });
    }

    const socket = new SockJS('http://v95239294g.goho.co/gs-guide-websocket');
    const stompClient = Stomp.over(socket);

    stompClient.connect({}, (frame) => {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/alarm', (message) => {
            const alarmParticulars = JSON.parse(message.body);
            console.log('Received alarm particulars:', alarmParticulars);

            // 更新高亮状态，并传递报警详细信息
            highlightPoint(alarmParticulars.alarmId, alarmParticulars);
        });
    }, (error) => {
        console.error('WebSocket error: ', error);
    });

    // 关闭浮窗逻辑
    document.getElementById("closePopup").addEventListener("click", () => {
        document.getElementById("infoPopup").style.display = "none";
    });

}).catch((e) => {
    console.error(e);
});










	//关闭某条警报
function closeone() {
	const imgclose = document.querySelector('.closeone')
	imgclose.addEventListener('click',() => {
		document.querySelector('.blackbody').style.display = 'none'
	})
}
closeone()

//警报
function al()
{
	myAxios({
		url:baseURL+'/alarm/getLatest',
		method:'POST',
		data:{

		}
	}).then(result => {
		console.log(result.data);
		const aldata = result.data
		document.querySelector('#parCon1').innerHTML = aldata.source
		document.querySelector('#parCon2').innerHTML = aldata.level
		document.querySelector('#parCon3').innerHTML = aldata.occurTime
		document.querySelector('#parCon4').innerHTML = aldata.type
		document.querySelector('#parCon5').innerHTML = aldata.additionalInfo
	}).catch(error => {
		console.dir(error)
	})
}
al()

//定时产生警报
function altime()
{
	const a = setTimeout(function(){
		document.querySelector('.blackbody').style.display = 'block'
		console.log(document.querySelector('.blackbody'));
		console.log(12345);
		clearTimeout(a)
	},20000)
	
}
// altime()