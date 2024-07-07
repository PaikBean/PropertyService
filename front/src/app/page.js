'use client'
import { useRouter } from 'next/navigation' // useRouter 훅을 임포트합니다.

import SilhouetteLicense from '@/components/typography/SilhouetteLicense'
import { Box, Grid, Typography, Avatar, CircularProgress } from '@mui/material'
import LockOutlinedIcon from '@mui/icons-material/LockOutlined'
import InputEmail from '@/components/textfield/InputEmail'
import InputPassword from '@/components/textfield/InputPassword'
import LoginBtn from '@/components/button/LoginBtn'
import SignUpLink from '@/components/typography/SignupLink'
import FindPasswordLink from '@/components/typography/FindPasswordLink'
import { useDispatch, useSelector } from 'react-redux'
import { loginUser } from '@/store/slices/authSlice'
import RegistCompanyLink from '@/components/typography/RegistCompanyLink'
import { useEffect, useState } from 'react'

export default function Home() {
  const router = useRouter() // useRouter 훅으로 router 객체를 가져옵니다.
  const dispatch = useDispatch()
  const { user, status, error } = useSelector((state) => state.auth)
  const [loading, setLoading] = useState(false)

  const [theme, setTheme] = useState(null)

  useEffect(() => {
    const loadTheme = async () => {
      const { createTheme } = await import('@mui/material/styles')
      setTheme(createTheme())
    }
    loadTheme()
  }, [])

  if (!theme)
    return (
      <Box
        sx={{
          display: 'flex',
          justifyContent: 'center',
          alignItems: 'center',
          height: '100vh',
          backgroundColor: '#f5f5f5',
        }}
      >
        <CircularProgress />
      </Box>
    )

  const handleSubmit = async (event) => {
    event.preventDefault()
    setLoading(true)
    const data = new FormData(event.currentTarget)
    const email = data.get('email')
    const password = data.get('password')
    const resultAction = await dispatch(loginUser({ email, password }))
    setLoading(false)

    if (loginUser.fulfilled.match(resultAction)) {
      router.push('/main')
    } else {
      alert('로그인 실패: ' + (resultAction.payload || 'Unknown error'))
    }
  }
  return (
    <>
      {' '}
      <Grid container>
        <Grid
          item
          xs
          sx={{
            position: 'relative',
            backgroundImage:
              "url('/silhouette-skyline-illustration/78786.jpg')",
            backgroundSize: 'cover',
            backgroundPosition: 'center',
            backgroundRepeat: 'no-repeat',
            flex: 1,
          }}
        >
          <SilhouetteLicense />
        </Grid>
        <Grid
          item
          sx={{
            height: '100vh',
            width: '500px', // 고정된 너비 값을 설정합니다
          }}
        >
          <Box
            sx={{
              mt: '150px',
              paddingX: '20px',
              width: '100%',
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <Avatar sx={{ m: 1, bgcolor: 'secondary' }}>
              <LockOutlinedIcon />
            </Avatar>
            <Typography component="h1" variant="h5">
              Sign in
            </Typography>
            <Box component="form" onSubmit={handleSubmit} sx={{ mt: 2 }}>
              <InputEmail />
              <InputPassword />
              <LoginBtn />
            </Box>
            <Grid container justifyContent="space-between" pt={3}>
              <Grid item>
                <RegistCompanyLink setLoading={setLoading} />
              </Grid>
              <Grid item>
                <SignUpLink setLoading={setLoading} />
              </Grid>
              <Grid item>
                <FindPasswordLink setLoading={setLoading} />
              </Grid>
            </Grid>
          </Box>
        </Grid>
        {loading && (
          <Box
            sx={{
              position: 'fixed',
              width: '100%',
              height: '100%',
              backgroundColor: 'rgba(0, 0, 0, 0.4)',
              display: 'flex',
              justifyContent: 'center',
              alignItems: 'center',
              zIndex: 9999,
            }}
          >
            <CircularProgress color="inherit" />
          </Box>
        )}
      </Grid>
    </>
  )
}
