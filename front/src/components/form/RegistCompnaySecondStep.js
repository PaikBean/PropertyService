import { useEffect, useState } from 'react'
import Gender from '../autocomplete/Gender'
import ManagerState from '../autocomplete/ManagerState'
import CheckSignUpPassword from '../textfield/CheckSignUpPassword'
import InputName2 from '../textfield/InputName2'
import InputPhoneNumber from '../textfield/InputPhoneNumber'
import InputSignUpEmail from '../textfield/InputSignUpEmail'
import InputSignUpPassword from '../textfield/InputSignUpPassword'

const { Stack, Grid, Button, Alert } = require('@mui/material')

const RegistCompanySecondStep = ({
  inputSecond,
  setInputSecond,
  stepFlag,
  handleValidEmail,
}) => {
  const [checkError, setCheckError] = useState(false)
  const handleInputChange = (field, value) => {
    setInputSecond((prev) => ({
      ...prev,
      [field]: value,
    }))
  }

  useEffect(() => {
    setCheckError(
      inputSecond.checkPassword.length > 0 &&
        inputSecond.presidentPassword !== inputSecond.checkPassword
    )
  }, [inputSecond.presidentPassword, inputSecond.checkPassword])

  return (
    <Stack spacing={2} width="70%">
      <Grid container gap={1} justifyContent={'space-between'}>
        <Grid item xs={4}>
          <InputName2
            label="대표자명"
            value={inputSecond.presidentName}
            onChange={(e) => {
              handleInputChange('presidentName', e.target.value)
            }}
          />
        </Grid>
        <Grid item xs={7.5}>
          <InputPhoneNumber
            value={inputSecond.presidentPhoneNumber}
            onChange={(formattedPhoneNumber) =>
              handleInputChange('presidentPhoneNumber', formattedPhoneNumber)
            }
            name="presidentPhoneNumber"
            label="대표자 전화번호"
          />
        </Grid>
      </Grid>
      <Grid container gap={1} justifyContent={'space-between'}>
        <Grid item xs={5.8}>
          <InputName2
            label="직급"
            value={inputSecond.presidentRank}
            onChange={(e) => {
              handleInputChange('presidentRank', e.target.value)
            }}
          />
        </Grid>
        <Grid item xs={5.8}>
          <InputName2
            label="직무"
            value={inputSecond.presidentPosition}
            onChange={(e) => {
              handleInputChange('presidentPosition', e.target.value)
            }}
          />
        </Grid>
      </Grid>
      <Grid container gap={1} justifyContent={'space-between'}>
        <Grid item xs={5.8}>
          <ManagerState
            value={inputSecond.presidentStateId} // Add this line
            onChange={(value) => {
              handleInputChange('presidentStateId', value)
            }}
            label="근무 상태"
          />
        </Grid>
        <Grid item xs={5.8}>
          <Gender
            value={inputSecond.presidentGender}
            onChange={(value) => {
              handleInputChange('presidentGender', value)
            }}
            label="성별"
          />
        </Grid>
      </Grid>
      <Grid container gap={1} justifyContent={'space-between'}>
        <Grid item xs={9.5}>
          <InputSignUpEmail
            value={inputSecond.presidentEmail}
            onChange={(e) => {
              handleInputChange('presidentEmail', e.target.value)
            }}
            label="대표자 이메일"
          />
        </Grid>
        <Grid item xs={2}>
          <Button
            onClick={handleValidEmail}
            fullWidth
            variant="contained"
            sx={{
              height: '55px',
              backgroundColor: '#56866fec',
              '&:hover': {
                backgroundColor: '#56866f',
              },
            }}
          >
            중복 확인
          </Button>
        </Grid>
      </Grid>
      {inputSecond.duplicateEmail ? (
        stepFlag.secondEmail ? (
          <Alert>중복 확인되었습니다.</Alert>
        ) : (
          <Alert severity="error">등록된 이메일 입니다.</Alert>
        )
      ) : null}
      <InputSignUpPassword
        value={inputSecond.presidentPassword}
        onChange={(e) => {
          handleInputChange('presidentPassword', e.target.value)
        }}
      />
      <CheckSignUpPassword
        value={inputSecond.checkPassword}
        onChange={(e) => {
          handleInputChange('checkPassword', e.target.value)
          handleInputChange(
            'checkResult',
            inputSecond.presidentPassword &&
              e.target.value &&
              inputSecond.presidentPassword === e.target.value
          )
        }}
        error={checkError}
      />
    </Stack>
  )
}

RegistCompanySecondStep.displayName = 'RegistCompanySecondStep'

export default RegistCompanySecondStep
