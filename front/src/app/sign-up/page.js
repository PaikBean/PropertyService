'use client'
import {
  Grid,
  Step,
  StepLabel,
  Stepper,
  Box,
  Button,
  Container,
  Stack,
  Alert,
} from '@mui/material'
import { useEffect, useState } from 'react'
import SignUpFirstStep from '@/components/form/SignUpFirstStep'
import SignUpSecondStep from '@/components/form/SignUpSecondStep'
import SignUpThirdStep from '@/components/form/SignUpThirdStep'
import { useDispatch, useSelector } from 'react-redux'
import SignupFourthStep from '@/components/form/SignUpFourthStep'
import {
  fetchSignUp,
  fetchSignUpApiCallTest,
  setBasicInfo,
  setDetailInfo,
  setLoginInfo,
} from '@/store/slices/signUpSlice'
import { useRouter } from 'next/navigation'

const steps = [
  'Validate Company',
  'Basic Info',
  'Detail Info',
  'Set Login Info',
]

export default function SignUp() {
  const router = useRouter
  const dispatch = useDispatch()

  const [currentStep, setCurrentStep] = useState(0)
  const [showAlert, setShowAlert] = useState(false)
  const [inputSecond, setInputSecond] = useState({
    managerName: '',
    managerPhoneNumber: '',
    managerAddressLevel1: '',
    managerAddressLevel2: '',
    managerAddressLevel3: '',
    gender: '',
  })
  const [inputThird, setInputThird] = useState({
    departmentId: '',
    managerRank: '',
    managerPosition: '',
    state: '',
    managerCode: '',
  })
  const [inputFourth, setInputFourth] = useState({
    managerEmail: '',
    managerPassword: '',
    duplicateEmail: false,
    checkPassword: '',
    checkResult: false,
  })

  const { companyInfo } = useSelector((state) => state.company)
  const signUpData = useSelector((state) => state.signUp)

  const handleSubmit = async (event) => {
    event.preventDefault()
    if (
      currentStep === 3 &&
      !validInputFourth() &&
      inputFourth.duplicateEmail
    ) {
      setShowAlert(true)
      setTimeout(() => {
        setShowAlert(false)
      }, 3000)
      return
    }
    // 회원가입 요청 보내기
    dispatch(setBasicInfo(inputSecond))
    dispatch(setDetailInfo(inputThird))
    dispatch(setLoginInfo(inputFourth))
    dispatch(fetchSignUp(signUpData))
      .unwrap()
      .then(() => {
        // alert('가입완료!')
        dispatch(clearSignup())
        router.push('/')
      })
      .catch((error) => {
        // alert(`회원가입 실패: ${error}`)
      })
    // dispatch(clearSignup())
  }

  const validInputSecond = () => {
    return !inputSecond.managerName ||
      !inputSecond.managerPhoneNumber ||
      !inputSecond.managerAddressLevel1 ||
      !inputSecond.managerAddressLevel2 ||
      !inputSecond.managerAddressLevel3 ||
      !inputSecond.gender
      ? false
      : true
  }

  const validInputThird = () => {
    return !inputThird.departmentId ||
      !inputThird.managerRank ||
      !inputThird.managerPosition ||
      !inputThird.state ||
      !inputThird.managerCode
      ? false
      : true
  }

  const validInputFourth = () => {
    return !inputFourth.managerEmail || !inputFourth.managerPassword
      ? false
      : true
  }

  const handleNext = () => {
    if (currentStep === 0 && !companyInfo) {
      setShowAlert(true)
      setTimeout(() => {
        setShowAlert(false)
      }, 3000)
      return
    }
    if (currentStep === 1 && !validInputSecond()) {
      setShowAlert(true)
      setTimeout(() => {
        setShowAlert(false)
      }, 3000)
      return
    }
    if (currentStep === 2 && !validInputThird()) {
      setShowAlert(true)
      setTimeout(() => {
        setShowAlert(false)
      }, 3000)
      return
    }
    setShowAlert(false)
    setCurrentStep((prevStep) => prevStep + 1)
  }

  const handleBack = () => {
    setCurrentStep((prevStep) => prevStep - 1)
  }

  const getStepContent = (step) => {
    switch (step) {
      case 0:
        return <SignUpFirstStep />
      case 1:
        return (
          <SignUpSecondStep
            inputSecond={inputSecond}
            setInputSecond={setInputSecond}
          />
        )
      case 2:
        return (
          <SignUpThirdStep
            inputThird={inputThird}
            setInputThird={setInputThird}
          />
        )
      case 3:
        return (
          <SignupFourthStep
            inputFourth={inputFourth}
            setInputFourth={setInputFourth}
          />
        )
      default:
        return 'Unknown step'
    }
  }
  return (
    <Container
      sx={{
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
        minHeight: '100vh',
        maxWidth: '1000px',
      }}
    >
      <Stack gap={5} width="100%">
        <Box
          sx={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            width: '100%',
          }}
        >
          {getStepContent(currentStep)}
          {showAlert ? (
            <Alert severity="error" sx={{ mt: '20px' }}>
              Check Your Input Value
            </Alert>
          ) : null}
        </Box>
        <Stepper activeStep={currentStep} alternativeLabel>
          {steps.map((label, index) => {
            return (
              <Step key={index}>
                <StepLabel>{label}</StepLabel>
              </Step>
            )
          })}
        </Stepper>
        <Button
          onClick={() => {
            dispatch(fetchSignUpApiCallTest({}))
              .unwrap()
              .then(() => {
                console.log('성공')
                // alert('가입완료!')
                // dispatch(clearSignup())
              })
              .catch((error) => {
                console.log('실패')
                console.log(error)
                // alert(`회원가입 실패: ${error}`)
              })
          }}
        >
          API 테스트용
        </Button>
        <Grid container>
          <Button
            disabled={currentStep === 0}
            onClick={handleBack}
            sx={{ mr: 1 }}
          >
            뒤로
          </Button>
          <Box sx={{ flex: '1 1 auto' }} />
          {currentStep === steps.length - 1 ? (
            <Button onClick={handleSubmit}>완료</Button>
          ) : (
            <Button onClick={handleNext}>다음</Button>
          )}
        </Grid>
      </Stack>
    </Container>
  )
}
