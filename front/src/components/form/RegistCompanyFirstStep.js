// React, Next
import { useDispatch } from 'react-redux'

// Materials
import { Grid, Stack } from '@mui/material'
import { LocalizationProvider } from '@mui/x-date-pickers'
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs'

// Custom Components
import InputBizNumber from '../textfield/InputBizNumber'
import BasicDatePicker from '../datepicker/BasicDatePicker'
import InputName2 from '../textfield/InputName2'
import SearchBtn from '../button/SearchBtn'

// Utils
import dayjs from 'dayjs'

const RegistCompanyFirstStep = ({
  inputFirst,
  setInputFirst,
  onClick,
  readOnly,
}) => {
  const dispatch = useDispatch()
  const handleInputChange = (field, value) => {
    setInputFirst((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  return (
    <Stack gap={5} width="80%">
      <InputBizNumber
        value={inputFirst.bizNumber}
        onChange={(e) => {
          handleInputChange('bizNumber', e.target.value)
        }}
        readOnly={readOnly}
        sx={{
          '& .MuiInputBase-root': {
            backgroundColor: readOnly ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
            cursor: readOnly ? 'not-allowed' : 'inherit', // 커서 변경
          },
        }}
      />
      <Grid container spacing={2}>
        <Grid item xs={6}>
          <LocalizationProvider dateAdapter={AdapterDayjs}>
            <BasicDatePicker
              label="등록일"
              value={dayjs(inputFirst.bizStartDate)}
              onChange={(value) => {
                if (value) {
                  // Check if value is not null or undefined
                  handleInputChange('bizStartDate', value.format('YYYYMMDD'))
                } else {
                  // Handle the case where date is cleared or invalid
                  handleInputChange('bizStartDate', '')
                }
              }}
              readOnly={readOnly}
            />
          </LocalizationProvider>
        </Grid>
        <Grid item xs={6}>
          <InputName2
            label="대표자명"
            value={inputFirst.presidentName}
            onChange={(e) => {
              handleInputChange('presidentName', e.target.value)
            }}
            readOnly={readOnly}
            sx={{
              '& .MuiInputBase-root': {
                backgroundColor: readOnly ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                cursor: readOnly ? 'not-allowed' : 'inherit', // 커서 변경
              },
            }}
          />
        </Grid>
      </Grid>
      <SearchBtn onClick={onClick} />
    </Stack>
  )
}

RegistCompanyFirstStep.displayName = 'RegistCompanyFirstStep'

export default RegistCompanyFirstStep
