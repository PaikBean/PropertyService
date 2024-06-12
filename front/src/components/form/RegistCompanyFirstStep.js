import { Grid, Stack, Typography } from '@mui/material'
import InputBizNumber from '../textfield/InputBizNumber'
import BasicDatePicker from '../datepicker/BasicDatePicker'
import { LocalizationProvider } from '@mui/x-date-pickers'
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs'
import InputName2 from '../textfield/InputName2'
import dayjs from 'dayjs'
import SearchBtn from '../button/SearchBtn'
import { useDispatch } from 'react-redux'
import { fetchValidBizNumber } from '@/store/slices/registCompanySlice'

const RegistCompanyFirstStep = ({ inputFirst, setInputFirst }) => {
  const dispatch = useDispatch()
  const handleInputChange = (field, value) => {
    setInputFirst((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  const handleSearch = () => {
    dispatch(fetchValidBizNumber(inputFirst))
  }

  return (
    <Stack gap={5} width="50%">
      <InputBizNumber
        value={inputFirst.bizNumber}
        onChange={(e) => {
          handleInputChange('bizNumber', e.target.value)
        }}
      />
      <Grid container spacing={2}>
        <Grid item xs={6}>
          <LocalizationProvider dateAdapter={AdapterDayjs}>
            <BasicDatePicker
              label="등록일"
              value={dayjs(inputFirst.bizStartDate)}
              onChange={(value) => {
                handleInputChange('bizStartDate', value.format('YYYYMMDD'))
              }}
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
          />
        </Grid>
      </Grid>
      <SearchBtn onClick={handleSearch} />
    </Stack>
  )
}

RegistCompanyFirstStep.displayName = 'RegistCompanyFirstStep'

export default RegistCompanyFirstStep
