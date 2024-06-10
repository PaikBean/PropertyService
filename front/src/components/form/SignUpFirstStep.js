import { useDispatch, useSelector } from 'react-redux'
import { useState } from 'react'
import { Stack, Grid } from '@mui/material'
import InputCompanyCode from '../textfield/InputCompanyCode'
import CompanyName from '../textfield/CompanyName'
import CompanyBizNum from '../textfield/CompanyBizNum'
import CompanyPresidentName from '../textfield/CompanyPresidentName'
import SearchCompanyBtn from '../button/SearchCompanyBtn'
import { fetchCompanyDetails } from '@/store/slices/companySlice'
import { setCompanyInfo } from '@/store/slices/signUpSlice'
const SignUpFirstStep = () => {
  const dispatch = useDispatch()
  const [inputCompanyCode, setInputCompanyCode] = useState('')
  const { companyInfo, status, error } = useSelector((state) => state.company)

  const handleSearch = () => {
    dispatch(fetchCompanyDetails(inputCompanyCode))
    dispatch(setCompanyInfo(inputCompanyCode))
  }

  return (
    <Stack gap={1} width="50%">
      <Grid container spacing={2}>
        <Grid item xs={9.5}>
          <InputCompanyCode
            value={inputCompanyCode}
            onChange={(e) => {
              setInputCompanyCode(e.target.value)
            }}
          />
        </Grid>
        <Grid item xs={2}>
          <SearchCompanyBtn onClick={handleSearch} />
        </Grid>
      </Grid>
      <CompanyName value={companyInfo ? companyInfo.companyName : ''} />
      <CompanyBizNum value={companyInfo ? companyInfo.companyBizNumber : ''} />
      <CompanyPresidentName
        value={companyInfo ? companyInfo.companyPresidentName : ''}
      />
    </Stack>
  )
}

SignUpFirstStep.displayName = 'SignUpFirstStep'

export default SignUpFirstStep
