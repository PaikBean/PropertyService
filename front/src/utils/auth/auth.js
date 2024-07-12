import { fetchPost } from '../fetch/fetchWrapper'

export const login = async (data) => {
  try {
    const params = {
      email: data.get('email'),
      password: data.get('password'),
    }
    const response = await fetch('/api/manager/v1/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(params),
    })
    if (response.ok && response.status === 200) {
      localStorage.setItem('token', response.headers.get('Authorization'))
      return await response.json()
    } else {
      console.error('Login failed:', response.statusText)
      throw new Error('Login failed')
    }
  } catch (error) {
    console.error('Error fetching data:', error)
    throw error
  }
}
