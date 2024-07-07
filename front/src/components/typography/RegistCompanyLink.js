import { Typography } from '@mui/material'
import { useRouter } from 'next/navigation'

const RegistCompanyLink = ({ setLoading }) => {
  const router = useRouter()
  const handleClick = (e) => {
    e.preventDefault()
    setLoading(true)
    router.push('/regist-company')
  }
  return (
    <Typography variant="body2" color="text.secondary" align="center">
      <a
        // href="/regist-company"
        onClick={handleClick}
        style={{
          color: 'inherit', // 부모 Typography의 색상을 상속받습니다.
          textDecoration: 'none', // 밑줄을 제거합니다.
        }}
      >
        {'사업체 등록'}
      </a>
    </Typography>
  )
}
RegistCompanyLink.displayName = 'RegistCompanyLink'

export default RegistCompanyLink
