'use client'
// React, Next
import { useState } from 'react'
import { useRouter } from 'next/navigation'

// Materials
import {
  Container,
  Stack,
  Step,
  StepLabel,
  Stepper,
  Typography,
  Box,
  Divider,
  Alert,
  Grid,
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
} from '@mui/material'

// Custom Component
import SubmitBtn from '@/components/button/SubmitBtn'
import NextBtn from '@/components/button/NextBtn'
import BackBtn from '@/components/button/BackBtn'
import RegistCompanyFirstStep from '@/components/form/RegistCompanyFirstStep'
import RegistCompanySecondStep from '@/components/form/RegistCompnaySecondStep'
import RegistCompanyThirdStep from '@/components/form/RegistCompanyThridStep'

// Utils
import { fetchValidBizNumber } from './api/fetchValidateBizNumber'
import { fetchDuplicateEmail } from './api/fetchDuplicateEmail'
import { fetchRegistCompany } from './api/fetchRegistCompany'

const steps = ['사업체 인증', '대표자 등록', '사업체 정보 등록']

const TestPage = () => {
  const router = useRouter()
  const initValidBiz = {
    bizNumber: '',
    bizStartDate: null,
    presidentName: '',
    flag: false,
  }

  const initRegistPresident = {
    presidentName: '',
    presidentRank: '',
    presidentPosition: '',
    presidentStateId: null,
    presidentGender: '',
    presidentPhoneNumber: '',
    presidentEmail: '',
    duplicateEmail: false,
    presidentPassword: '',
    checkPassword: '',
    checkResult: false,
  }

  const initRegistCompany = {
    companyName: '',
    companyEmail: '',
    companyNumber: '',
    companyServiceStartDate: '',
  }

  const initStepFlag = {
    first: false,
    secondEmail: false,
    secondPassword: false,
    third: false,
  }

  const [currentStep, setCurrentStep] = useState(0)
  const [showBizNumberErrorAlert, setShowBizNumberErrorAlert] = useState(null)
  const [showNextAlert, setShowNextAlert] = useState(false)

  const [validBiz, setValidBiz] = useState(initValidBiz)

  const [registPresident, setRegistPresident] = useState(initRegistPresident)

  const [registCompany, setRegistCompany] = useState(initRegistCompany)

  const [stepFlag, setStepFlag] = useState(initStepFlag)

  const [companyCode, setCompanyCode] = useState('')

  const [isRegistDialogOpen, setIsRegistDialogOpen] = useState(false)

  const confirmYes = () => {
    setIsRegistDialogOpen(false)
    router.push('/')
  }

  const handleNext = () => {
    switch (currentStep) {
      case 0:
        if (stepFlag.first && !showBizNumberErrorAlert) {
          setCurrentStep(currentStep + 1)
          setShowNextAlert(!showNextAlert)
        }
      case 1:
        if (
          stepFlag.secondEmail &&
          registPresident.presidentEmail &&
          registPresident.checkResult
        ) {
          setCurrentStep(currentStep + 1)
        }
      case 2:
      case 3:
    }
    if (showNextAlert) {
    }
  }

  const handleBack = () => {
    currentStep > 0 ? setCurrentStep(currentStep - 1) : null
  }

  const handleSubmit = async () => {
    const data = {
      companyInfo: {
        companyName: registCompany.companyName,
        companyEmail: registCompany.companyEmail,
        companyNumber: registCompany.companyNumber,
        bizNumber: validBiz.bizNumber,
      },
      presidentInfo: {
        managerName: registPresident.presidentName,
        managerRank: registPresident.presidentRank,
        managerPosition: registPresident.presidentPosition,
        managerStateId: registPresident.presidentStateId,
        gender: registPresident.presidentGender,
        managerPhoneNumber: registPresident.presidentPhoneNumber,
        managerEmail: registPresident.presidentEmail,
        managerPassword: registPresident.presidentPassword,
      },
    }
    const result = await fetchRegistCompany(data)
    if (result.code === '200') {
      setCompanyCode(result.data.companyCode)
      setIsRegistDialogOpen(!isRegistDialogOpen)
    } else {
      alert('실패')
    }
  }

  const handleValidDuplicateEmail = async () => {
    const result = await fetchDuplicateEmail(registPresident.presidentEmail)
    if (result.code === '200') {
      setStepFlag((prev) => ({ ...prev, secondEmail: true }))
      setRegistPresident((prev) => ({ ...prev, duplicateEmail: true }))
    } else {
      // 중복
      setStepFlag((prev) => ({ ...prev, secondEmail: false }))
      setRegistPresident((prev) => ({ ...prev, duplicateEmail: true }))
    }
  }

  const handleValidateBizNum = async () => {
    const result = await fetchValidBizNumber(validBiz)
    if (result.code === '200') {
      // 유효성 검사 통과 시 처리 로직 추가
      setShowBizNumberErrorAlert(false)
      setShowNextAlert(true)
      setStepFlag((prev) => ({ ...prev, first: true }))
    } else {
      // 유효성 검사 실패 시 처리 로직 추가
      setShowNextAlert(false)
      setShowBizNumberErrorAlert(true)
    }
  }

  const getStepContent = (step) => {
    switch (step) {
      case 0:
        return (
          <RegistCompanyFirstStep
            inputFirst={validBiz}
            setInputFirst={setValidBiz}
            onClick={handleValidateBizNum}
          />
        )
      case 1:
        return (
          <RegistCompanySecondStep
            inputSecond={registPresident}
            setInputSecond={setRegistPresident}
            stepFlag={stepFlag}
            handleValidEmail={handleValidDuplicateEmail}
          />
        )
      case 2:
        return (
          <RegistCompanyThirdStep
            inputThird={registCompany}
            setInputThird={setRegistCompany}
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
        }}
      >
        <Typography variant="h4">사업체 등록</Typography>
        <Stack
          spacing={5}
          width={'100%'}
          sx={{
            alignItems: 'center',
          }}
        >
          <Divider />
          {getStepContent(currentStep)}
          {showBizNumberErrorAlert ? (
            <Alert severity="error" sx={{ mt: '20px' }}>
              국세청에 등록되지 않은 사업자 입니다.
            </Alert>
          ) : null}
          {showNextAlert ? (
            <Alert sx={{ mt: '20px' }}>확인되었습니다.</Alert>
          ) : null}
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
        onClose={() => setIsRegistDialogOpen(false)}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">회사 코드 발급 완료</DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            ComapnyCode : {companyCode} 가 발급되었습니다. 가입하신 이메일과
            비밀번호로 로그인 하시기 바랍니다.
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
