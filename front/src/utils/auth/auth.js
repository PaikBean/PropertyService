const login = async (email, password) => {
  try {
    let path = "/api/manager/v1/login"
    let data = {
      email : email,
      password: password
    }
    for (const [key, value] of Object.entries(data)) {
      path = path.replace(`:${key}`, value)
    }

    const queryString = new URLSearchParams(queryParams).toString()
    const finalUrl = queryString ? `${path}?${queryString}` : path
    console.log(finalUrl)
    const response = await fetch(finalUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ email, password }),
    });
    const result = await response.json();
  } catch (error) {
    console.error('Error:', error)
  }
}

export default login
