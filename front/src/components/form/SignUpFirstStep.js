import { useDispatch, useSelector } from 'react-redux'
import { useState } from 'react'
import { Stack, Grid, Divider, Alert } from '@mui/material'
import InputCompanyCode from '../textfield/InputCompanyCode'
import CompanyName from '../textfield/CompanyName'
import CompanyBizNum from '../textfield/CompanyBizNum'
import CompanyPresidentName from '../textfield/CompanyPresidentName'
import SearchBtn from '../button/SearchBtn'
import { fetchCompanyDetails } from '@/store/slices/companySlice'
import { setCompanyInfo } from '@/store/slices/signUpSlice'
import InputName2 from '../textfield/InputName2'
const SignUpFirstStep = ({
  validCompany,
  setValidCompany,
  onClick,
  readOnly,
}) => {
  const [showAlert, setShowAlert] = useState(validCompany.flag)
  const [showNextAlert, setShowNextAlert] = useState(readOnly)

  const handleInputChange = (field, value) => {
    setValidCompany((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  return (
    <Stack spacing={3} width="60%">
      <Grid container gap={2}>
        <Grid item xs={10}>
          <InputName2
            value={validCompany.companyCode}
            onChange={(e) => {
              handleInputChange('companyCode', e.target.value)
            }}
            readOnly={readOnly}
            sx={{
              '& .MuiInputBase-root': {
                backgroundColor: readOnly ? '#f5f5f5' : 'inherit', // 회색빛 배경 설정
                cursor: readOnly ? 'not-allowed' : 'inherit', // 커서 변경
              },
            }}
            label={'회사 코드'}
          />
        </Grid>
        <Grid item xs={1} alignSelf={'center'}>
          <SearchBtn onClick={onClick} />
        </Grid>
      </Grid>
      {validCompany.flag && readOnly ? (
        <Alert sx={{ mt: '20px' }}>확인되었습니다.</Alert>
      ) : validCompany.flag ? (
        <Alert severity="error" sx={{ mt: '20px' }}>
          등록되지 않은 회사코드 입니다.
        </Alert>
      ) : null}
      <Divider />
      <Grid container gap={1}>
        <Grid item xs={5.8}>
          <InputName2
            value={validCompany.companyName}
            onChange={(e) => {
              handleInputChange('companyName', e.target.value)
            }}
            readOnly={false}
            sx={{
              '& .MuiInputBase-root': {
                backgroundColor: '#f5f5f5', // 회색빛 배경 설정
                cursor: 'not-allowed', // 커서 변경
              },
            }}
            label={'회사명'}
          />
        </Grid>
        <Grid item xs={5.8}>
          <InputName2
            value={validCompany.companyPresidnetName}
            onChange={(e) => {
              handleInputChange('companyPresidnetName', e.target.value)
            }}
            readOnly={false}
            sx={{
              '& .MuiInputBase-root': {
                backgroundColor: '#f5f5f5', // 회색빛 배경 설정
                cursor: 'not-allowed', // 커서 변경
              },
            }}
            label={'대표자'}
          />
        </Grid>
      </Grid>
      <InputName2
        value={validCompany.companyBizNumber}
        onChange={(e) => {
          handleInputChange('companyBizNumber', e.target.value)
        }}
        readOnly={false}
        sx={{
          '& .MuiInputBase-root': {
            backgroundColor: '#f5f5f5', // 회색빛 배경 설정
            cursor: 'not-allowed', // 커서 변경
          },
        }}
        label={'사업자 등록 번호'}
      />
    </Stack>
  )
}

export default SignUpFirstStep
