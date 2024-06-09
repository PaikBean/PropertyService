const login = async (email, password) => {
  try {
    // const response = await fetch('/api/sign-up', {
    //   method: 'POST',
    //   headers: {
    //     'Content-Type': 'application/json',
    //   },
    //   body: JSON.stringify({ email, password }),
    // });
    // const data = await response.json();
    // return data;
    return JSON.stringify({ email, password })
  } catch (error) {
    console.error('Error:', error)
  }
}

export default login
