import { TextField } from '@mui/material'
import { useEffect, useState } from 'react'

const CashTextField = ({ label, value, onChange }) => {
  const [formattedValue, setFormattedValue] = useState('')

  useEffect(() => {
    setFormattedValue(formatValue(value))
  }, [value])

  const handleChange = (e) => {
    const inputValue = e.target.value.replace(/,/g, '')
    if (/^\d*$/.test(inputValue)) {
      setFormattedValue(formatValue(inputValue))
      onChange({ target: { value: inputValue } })
    }
  }

  const formatValue = (val) => {
    if (val == null) return ''
    return val.toString().replace(/\B(?=(\d{4})+(?!\d))/g, ',')
  }

  return (
    <TextField
      label={label}
      value={formattedValue}
      onChange={handleChange}
      variant="outlined"
      InputProps={{
        endAdornment: <span>원</span>,
      }}
    />
  )
}

CashTextField.displayName = 'CashTextField'

export default CashTextField
