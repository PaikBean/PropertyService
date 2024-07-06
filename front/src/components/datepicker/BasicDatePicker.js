import { TextField } from '@mui/material'
import { DatePicker } from '@mui/x-date-pickers'

const BasicDatePicker = ({ label, value, onChange, readOnly = false }) => {
  return (
    <DatePicker
      label={label}
      value={value}
      onChange={onChange}
      format="YYYY.MM.DD"
      slotProps={{
        textField: {
          variant: 'outlined',
          error: false,
          InputProps: {
            readOnly: readOnly,
          },
          sx: {
            '& .MuiInputBase-root': {
              backgroundColor: readOnly ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
              cursor: readOnly ? 'not-allowed' : 'inherit', // 커서 변경
            },
          },
        },
      }}
      disabled={readOnly}
    />
  )
}

BasicDatePicker.displayName = 'BasicDatePicker'

export default BasicDatePicker
