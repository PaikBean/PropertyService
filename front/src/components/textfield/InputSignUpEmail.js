import { TextField } from '@mui/material'
import { useEffect, useState } from 'react'

const isValidEmail = (email) => {
  const emailRegex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i
  return emailRegex.test(email)
}

const InputSignUpEmail = ({
  value,
  onChange,
  sx,
  readOnly = false,
  label = 'Email Address',
}) => {
  const [error, setError] = useState(false)

  useEffect(() => {
    setError(value ? !isValidEmail(value) : false)
  }, [value])

  const handleChange = (e) => {
    const { value } = e.target
    onChange(e)
  }

  return (
    <TextField
      sx={sx}
      value={value}
      onChange={handleChange}
      InputProps={{
        readOnly: readOnly, // readOnly 속성 설정
      }}
      id="email"
      label={label}
      name="email"
      fullWidth
      variant="outlined"
      type="email"
      autoComplete="email"
      error={error}
      helperText={error ? 'Please enter a valid email address' : ''}
    />
  )
}

InputSignUpEmail.displayName = 'InputSignUpEmail'

export default InputSignUpEmail
