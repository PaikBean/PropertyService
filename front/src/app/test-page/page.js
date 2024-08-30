'use client'
// React, Next
import { useState } from 'react'
import { useRouter } from 'next/navigation'

// Materials
import {
  Box,
  Container,
  Divider,
  Grid,
  Stack,
  Step,
  StepLabel,
  Stepper,
  Typography,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  Button,
} from '@mui/material'

// Custom Component
import BackBtn from '@/components/button/BackBtn'
import NextBtn from '@/components/button/NextBtn'
import SubmitBtn from '@/components/button/SubmitBtn'
import SignUpFirstStep from '@/components/form/SignUpFirstStep'
import SignUpSecondStep from '@/components/form/SignUpSecondStep'
import SignUpThirdStep from '@/components/form/SignUpThirdStep'

// Utils
import { fetchREgistManager } from './api/fetchRegistManager'
import { fetchValidCompanyCode } from './api/fetchValidCompanyCode'
import { fetchDuplicateEmail } from '../regist-company/api/fetchDuplicateEmail'

const steps = ['회사 인증', '가입 정보', '계정 등록']

const TestPage = () => {
  const router = useRouter()
  const initValidCompany = {
    companyCode: '',
    companyName: '',
    companyPresidnetName: '',
    companyBizNumber: '',
    flag: null,
  }

  const initManagerInfo = {
    managerName: '',
    managerGender: '',
    departmentId: null,
    managerRank: '',
    managerPosition: '',
    managerStateId: null,
    managerPhoneNumber: '',
  }

  const initRegistAccount = {
    managerEmail: '',
    duplicateEmail: false,
    managerPassword: '',
    checkPassword: '',
    checkResult: false,
  }

  const initStepFlag = {
    first: false, // 회사 코드 검증
    second: false, // 전체 입력 완성
    thirdEmail: false, // 이메일 중복검사
    thirdPassword: false, // 비밀번호 일치
  }

  const [currentStep, setCurrentStep] = useState(0)

  const [validCompany, setValidCompany] = useState(initValidCompany)
  const [managerInfo, setManagerInfo] = useState(initManagerInfo)
  const [registAccount, setRegistAccount] = useState(initRegistAccount)
  const [stepFlag, setStepFlag] = useState(initStepFlag)

  const [isRegistDialogOpen, setIsRegistDialogOpen] = useState(false)

  const confirmYes = () => {
    setIsRegistDialogOpen(false)
    setValidCompany(initValidCompany)
    setManagerInfo(initManagerInfo)
    setRegistAccount(initRegistAccount)
    router.push('/')
  }

  const handleBack = () => {
    currentStep > 0 ? setCurrentStep(currentStep - 1) : null
  }

  const handleNext = () => {
    switch (currentStep) {
      case 0:
        if (stepFlag.first && validCompany.flag) {
          setCurrentStep(currentStep + 1)
        }
      case 1:
        if (checkAllSecond) {
          setCurrentStep(currentStep + 1)
        }
    }
  }

  const handleValidDuplicateEmail = async () => {
    const result = await fetchDuplicateEmail(registAccount.managerEmail)
    if (result.code === '200') {
      setStepFlag((prev) => ({ ...prev, thirdEmail: true }))
      setRegistAccount((prev) => ({ ...prev, duplicateEmail: true }))
    } else {
      // 중복
      setStepFlag((prev) => ({ ...prev, thirdEmail: false }))
      setRegistAccount((prev) => ({ ...prev, duplicateEmail: true }))
    }
  }

  const handleSubmit = async () => {
    const data = {
      companyCode: validCompany.companyCode,
      managerName: managerInfo.managerName,
      managerPhoneNumber: managerInfo.managerPhoneNumber,
      gender: managerInfo.managerGender,
      departmentId: managerInfo.departmentId,
      managerRank: managerInfo.managerRank,
      managerPosition: managerInfo.managerPosition,
      managerStateId: managerInfo.managerStateId,
      managerEmail: registAccount.managerEmail,
      managerPassword: registAccount.managerPassword,
    }
    const result = await fetchREgistManager(data)
    if (result.code === '201') {
      setIsRegistDialogOpen(!isRegistDialogOpen)
    } else {
      alert('실패')
    }
  }

  const checkAllSecond = () => {
    return Object.values(managerInfo).every(
      (field) => field !== '' && field !== null
    )
  }

  const validCompanyCode = async () => {
    const result = await fetchValidCompanyCode(validCompany.companyCode)
    if (result.code === '200') {
      setValidCompany((prev) => ({
        ...prev,
        companyName: result.data.companyName,
        companyPresidnetName: result.data.companyPresidnetName,
        companyBizNumber: result.data.companyBizNumber,
        flag: true,
      }))
      setStepFlag((prev) => ({ ...prev, first: true }))
    } else {
      setValidCompany((prev) => ({
        ...prev,
        flag: true,
      }))
    }
  }

  const getStepContent = (step) => {
    switch (step) {
      case 0:
        return (
          <SignUpFirstStep
            validCompany={validCompany}
            setValidCompany={setValidCompany}
            onClick={validCompanyCode}
            readOnly={stepFlag.first}
          />
        )
      case 1:
        return (
          <SignUpSecondStep
            managerInfo={managerInfo}
            setManagerInfo={setManagerInfo}
            companyCode={validCompany.companyCode}
          />
        )
      case 2:
        return (
          <SignUpThirdStep
            registAccount={registAccount}
            setRegistAccount={setRegistAccount}
            stepFlag={stepFlag}
            handleValidEmail={handleValidDuplicateEmail}
          />
        )
      default:
        return 'Unknown step'
    }
  }
  return (
    <Box
      sx={{
        // backgroundColor: '#56866f', // 연한 연두색 배경
        minHeight: '100vh', // 최소 높이를 화면 높이로 설정
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
      }}
    >
      <Container
        sx={{
          display: 'flex',
          flexDirection: 'column',
          justifyContent: 'center',
          alignItems: 'center',
          textAlign: 'center', // 텍스트 중앙 정렬
          backgroundColor: 'white', // Container 배경을 흰색으로 설정
          borderRadius: 5,
          boxShadow: 15,
          padding: 3,
          // border: '2px solid black',
          width: '700px',
          maxWidth: '90vw', // 모바일 화면에서 최대 너비를 제한합니다.
        }}
      >
        <Typography variant="h4">회원 가입</Typography>
        <Stack
          spacing={5}
          width={'100%'}
          sx={{
            alignItems: 'center',
          }}
        >
          <Divider />
          {getStepContent(currentStep)}
          <Stepper
            activeStep={currentStep}
            alternativeLabel
            sx={{
              width: '100%', // Stepper의 전체 너비를 설정
              '& .MuiStep-root': {
                flex: 1, // 각 스텝의 flex 속성을 1로 설정하여 전체 너비에 균등하게 분배되도록 함
              },
              '& .MuiStepConnector-root': {
                flex: 0, // 스텝 간의 커넥터의 flex 속성을 0으로 설정하여 간격을 조절
              },
            }}
          >
            {steps.map((label, index) => {
              return (
                <Step key={index}>
                  <StepLabel color="white">{label}</StepLabel>
                </Step>
              )
            })}
          </Stepper>
          <Grid container justifyContent={'space-between'}>
            <Grid item>
              {currentStep > 0 ? (
                <BackBtn onClick={handleBack}></BackBtn>
              ) : null}
            </Grid>
            <Grid item>
              {currentStep === steps.length - 1 ? (
                <SubmitBtn onClick={handleSubmit} />
              ) : (
                <NextBtn onClick={handleNext} />
              )}
            </Grid>
          </Grid>
        </Stack>
      </Container>
      <Dialog
        open={isRegistDialogOpen}
        onClose={(event, reason) => {
          if (reason !== 'backdropClick') {
            setIsRegistDialogOpen(false)
          }
        }}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
        maxWidth="md" // Dialog의 최대 너비를 설정합니다.
        fullWidth // Dialog가 최대 너비를 채우도록 설정합니다.
      >
        <DialogTitle id="alert-dialog-title">회원가입 완료</DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            회원가입이 완료되었습니다. <br />
            로그인해주세요.
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={confirmYes} color="primary" autoFocus>
            확인
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  )
}

export default TestPage
