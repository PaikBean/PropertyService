// React, Next
import { useRouter } from 'next/navigation'

// Materials
import { Typography } from '@mui/material'

// Custom Components

// Utils

const FindPasswordLink = ({ setLoading }) => {
  const router = useRouter()
  const handleClick = (e) => {
    e.preventDefault()
    setLoading(true)
    router.push('/initial-password')
  }
  return (
    <Typography variant="body2" color="text.secondary" align="center">
      <a
        onClick={handleClick}
        style={{
          color: 'inherit', // 부모 Typography의 색상을 상속받습니다.
          textDecoration: 'none', // 밑줄을 제거합니다.
        }}
      >
        {'비밀번호 찾기'}
      </a>
    </Typography>
  )
}

FindPasswordLink.displayName = 'FindPasswordLink'

export default FindPasswordLink
