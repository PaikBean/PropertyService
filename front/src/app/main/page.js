'use client'
// React, Next
import { useState } from 'react'
import { useRouter } from 'next/navigation'

// Materials
import {
  AppBar,
  Drawer,
  IconButton,
  List,
  Toolbar,
  Typography,
  ListItemText,
  ListItemButton,
  Collapse,
  Divider,
  Box,
} from '@mui/material'
import {
  ExpandLess, // 접기 아이콘
  ExpandMore, // 펼치기 아이콘
  People as PeopleIcon, // 고객장부 아이콘
  CalendarMonth, // 캘린더 아이콘
} from '@mui/icons-material'
import LogoutIcon from '@mui/icons-material/Logout' // 로그아웃 아이콘
import AccountBalanceWalletIcon from '@mui/icons-material/AccountBalanceWallet' // 영업장부 아이콘
import BarChartIcon from '@mui/icons-material/BarChart' // 매출장부 아이콘
import HomeIcon from '@mui/icons-material/Home'
import AccountBoxIcon from '@mui/icons-material/AccountBox'
import CorporateFareIcon from '@mui/icons-material/CorporateFare'

// Custom Componets

// Pages
import RegistRevenuePage from '@/pages/revenueLedger/RegistRevenuePage'
import RevenueListPage from '@/pages/revenueLedger/RevenueListPage'
import RegistClientPage from '@/pages/clientsLedger/RegistClientPage'
import ManageClientPage from '@/pages/clientsLedger/ManageClientPage'
import DetailClientPage from '@/pages/clientsLedger/DetailClientPage'
import MyPage from '@/pages/myPage/MyPage'
import ManageCompanyPage from '@/pages/manageCompany/ManageCompanyPage'
import SchedulePage from '@/pages/schedule/SchedulePage'
import DetailPropertyPage from '@/pages/salesLedger/DetailPropertyPage'
import RegistPropertyPage from '@/pages/salesLedger/RegistPropertyPage'
import ManageBuildingPage from '@/pages/salesLedger/ManageBuildingPage'
import ResgistBuildingPage from '@/pages/salesLedger/RegistBuildingPage'

// Utils

export default function Main() {
  const router = useRouter()
  const [activeMenu, setActiveMenu] = useState('')
  const [open, setOpen] = useState({
    salesLedger: false,
    clientLedger: false,
    revenueLedger: false,
  })

  const handleMenuClick = (menu) => () => {
    setActiveMenu(menu)
  }

  const handleClick = (menu) => {
    setOpen((prevOpen) => ({ ...prevOpen, [menu]: !prevOpen[menu] }))
  }

  const handleLogout = () => {
    router.push('/')
  }

  const handleHome = () => {
    setActiveMenu('')
    setOpen({
      salesLedger: false,
      clientLedger: false,
      revenueLedger: false,
    })
    router.push('/main')
  }

  return (
    <Box sx={{ display: 'flex' }}>
      <AppBar
        position="fixed"
        sx={{
          zIndex: (theme) => theme.zIndex.drawer + 1,
          backgroundColor: '#56866fec',
        }}
      >
        <Toolbar>
          <Typography variant="h6" noWrap component="div" sx={{ flexGrow: 1 }}>
            Property Service
          </Typography>
          <IconButton
            color="inherit"
            aria-label="logout"
            edge="end"
            onClick={handleLogout}
          >
            <LogoutIcon />
          </IconButton>
        </Toolbar>
      </AppBar>
      <Drawer
        variant="permanent"
        sx={{
          width: '180px',
          flexShrink: 0,
          '& .MuiDrawer-paper': {
            width: '180px',
            boxSizing: 'border-box',
            marginTop: '64px', // Height of the AppBar
          },
        }}
      >
        <List>
          <ListItemButton onClick={handleHome} sx={{ mb: 0.5 }}>
            <HomeIcon sx={{ mr: 2 }} />
            <ListItemText primary="홈" />
          </ListItemButton>

          <ListItemButton onClick={handleMenuClick('MyPage')} sx={{ mb: 0.5 }}>
            <AccountBoxIcon sx={{ mr: 2 }} />
            <ListItemText primary="마이 페이지" />
          </ListItemButton>

          <ListItemButton
            onClick={handleMenuClick('ManageCompany')}
            sx={{ mb: 0.5 }}
          >
            <CorporateFareIcon sx={{ mr: 2 }} />
            <ListItemText primary="조직 관리" />
          </ListItemButton>

          <ListItemButton
            onClick={handleMenuClick('Schedule')}
            sx={{ mb: 0.5 }}
          >
            <CalendarMonth sx={{ mr: 2 }} />
            <ListItemText primary="일정" />
          </ListItemButton>

          <Divider sx={{ mb: 1 }} />

          <ListItemButton
            onClick={() => handleClick('salesLedger')}
            sx={{ mb: 0.5 }}
          >
            <AccountBalanceWalletIcon sx={{ mr: 2 }} />
            <ListItemText primary="영업장부" />
            {open.salesLedger ? <ExpandLess /> : <ExpandMore />}
          </ListItemButton>
          <Collapse in={open.salesLedger} timeout="auto" unmountOnExit>
            <List component="div" disablePadding>
              <ListItemButton
                onClick={handleMenuClick('DetailProperty')}
                sx={{ pl: 8, py: 0.5 }}
              >
                <ListItemText primary="매물 상세" />
              </ListItemButton>
              {/* <ListItemButton
                onClick={handleMenuClick('ManageProperty')}
                sx={{ pl: 8, py: 0.5 }}
              >
                <ListItemText primary="매물 관리" />
              </ListItemButton> */}
              <ListItemButton
                onClick={handleMenuClick('RegistProperty')}
                sx={{ pl: 8, py: 0.5 }}
              >
                <ListItemText primary="매물 등록" />
              </ListItemButton>
              <ListItemButton
                onClick={handleMenuClick('ManageBuilding')}
                sx={{ pl: 8, py: 0.5 }}
              >
                <ListItemText primary="건물 관리" />
              </ListItemButton>
              <ListItemButton
                onClick={handleMenuClick('RegistBuildng')}
                sx={{ pl: 8, py: 0.5 }}
              >
                <ListItemText primary="건물 등록" />
              </ListItemButton>
            </List>
          </Collapse>

          <ListItemButton
            onClick={() => handleClick('clientLedger')}
            sx={{ mb: 0.5 }}
          >
            <PeopleIcon sx={{ mr: 2 }} />
            <ListItemText primary="고객장부" />
            {open.clientLedger ? <ExpandLess /> : <ExpandMore />}
          </ListItemButton>
          <Collapse in={open.clientLedger} timeout="auto" unmountOnExit>
            <List component="div" disablePadding>
              <ListItemButton
                sx={{ pl: 8, py: 0.5 }}
                onClick={handleMenuClick('DetailClient')}
              >
                <ListItemText primary="고객 상세" />
              </ListItemButton>
              <ListItemButton
                sx={{ pl: 8, py: 0.5 }}
                onClick={handleMenuClick('ManageClient')}
              >
                <ListItemText primary="고객 관리" />
              </ListItemButton>
              <ListItemButton
                sx={{ pl: 8, py: 0.5 }}
                onClick={handleMenuClick('RegistClient')}
              >
                <ListItemText primary="고객 등록" />
              </ListItemButton>
            </List>
          </Collapse>

          <ListItemButton
            onClick={() => handleClick('revenueLedger')}
            sx={{ mb: 0.5 }}
          >
            <BarChartIcon sx={{ mr: 2 }} />
            <ListItemText primary="매출장부" />
            {open.revenueLedger ? <ExpandLess /> : <ExpandMore />}
          </ListItemButton>
          <Collapse in={open.revenueLedger} timeout="auto" unmountOnExit>
            <List component="div" disablePadding>
              <ListItemButton
                sx={{ pl: 8, py: 0.5 }}
                onClick={handleMenuClick('RevenueList')}
              >
                <ListItemText primary="매출 목록" />
              </ListItemButton>
              <ListItemButton
                sx={{ pl: 8, py: 0.5 }}
                onClick={handleMenuClick('RegistLevenue')}
              >
                <ListItemText primary="매출 등록" />
              </ListItemButton>
            </List>
          </Collapse>
        </List>
      </Drawer>
      <Box
        component="main"
        sx={{
          flexGrow: 1,
          p: 3,
          marginTop: '64px', // Height of the AppBar
        }}
      >
        {activeMenu === 'MyPage' && <MyPage />}
        {activeMenu === 'ManageCompany' && <ManageCompanyPage />}
        {activeMenu === 'Schedule' && <SchedulePage />}
        {activeMenu === 'DetailProperty' && <DetailPropertyPage />}
        {/* {activeMenu === 'ManageProperty' && <ManagePropertyPage />} */}
        {activeMenu === 'RegistProperty' && <RegistPropertyPage />}
        {activeMenu === 'ManageBuilding' && <ManageBuildingPage />}
        {activeMenu === 'RegistBuildng' && <ResgistBuildingPage />}
        {activeMenu === 'DetailClient' && <DetailClientPage />}
        {activeMenu === 'ManageClient' && <ManageClientPage />}
        {activeMenu === 'RegistClient' && <RegistClientPage />}
        {activeMenu === 'RevenueList' && <RevenueListPage />}
        {activeMenu === 'RegistLevenue' && <RegistRevenuePage />}
      </Box>
    </Box>
  )
}
