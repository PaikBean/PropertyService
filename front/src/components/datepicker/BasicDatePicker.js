import { TextField } from '@mui/material'
import { DatePicker } from '@mui/x-date-pickers'

const BasicDatePicker = ({ label, value, onChange }) => {
  return (
    <DatePicker
      label={label}
      value={value}
      onChange={onChange}
      format="YYYY.MM.DD"
      slotProps={{
        textField: { variant: 'outlined', error: false },
      }}
    />
  )
}

BasicDatePicker.displayName = 'BasicDatePicker'

export default BasicDatePicker
