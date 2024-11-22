baseURL = 'http://v95239294g.goho.co'
baseURLGPT = 'http://v95239294g.goho.co:24688'
axios.defaults.baseURL = 'http://v95239294g.goho.co'


//请求拦截器
axios.interceptors.request.use(function (config) {
  const token = localStorage.getItem('token')
  token && (config.headers.Authorization == `Bearer ${token}`)
  return config
},function (error) {
  return Promise.reject(error)
})

//响应拦截器
axios.interceptors.response.use(function (response) {
  return response;
},function (error) {
  if(error?.response?.status === 401) {
    alert('登录状态过期，请重新登录')
    localStorage.clear()
    location.href = './login.html'
  }
  return Promise.reject(error)
})

//调用接口
function myAxios(config){
	return new Promise((resolve,reject) => {
		const xhr = new XMLHttpRequest()
		if(config.params) {
			const paramsObj = new URLSearchParams(config.params)
			const queryString = paramsObj.toString()
			config.url += `?${queryString}`
		}
		xhr.open(config.method || 'GET' , config.url)
		xhr.addEventListener('loadend' , () => {
			if(xhr.status >=200 && xhr.status <300) {
				resolve(JSON.parse(xhr.response))
			} else {
				reject(new Error(xhr.response))
			}
		})
		const token = localStorage.getItem('token')
		xhr.setRequestHeader('Authorization',`Bearer ${token}`)
		if(config.data) {
			const jsonStr = JSON.stringify(config.data)
			xhr.setRequestHeader('Content-Type','application/json')
			xhr.send(jsonStr)
		}else{
			xhr.send()
		}
	})
}



