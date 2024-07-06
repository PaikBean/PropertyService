import { TextField } from '@mui/material'
import { useEffect, useState } from 'react'

const CashTextField = ({ label, value, onChange, readOnly = false }) => {
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
        readOnly: readOnly, // readOnly 속성 설정
      }}
      sx={{
        '& .MuiInputBase-root': {
          backgroundColor: readOnly ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
          cursor: readOnly ? 'not-allowed' : '', // 커서 변경
        },
      }}
    />
  )
}

CashTextField.displayName = 'CashTextField'

export default CashTextField
