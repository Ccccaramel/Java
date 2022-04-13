export default [
  {
    path:'/',
    name:'GMLogin',
    component:()=>import('@/views/GMLogin.vue')
  },
  {
    path:'/ManagementSystem',
    name:'ManagementSystem',
    component:()=>import('@/views/ManagementSystem.vue')
  },
  {
    path:'/AddGM',
    name:'AddGM',
    component:()=>import('@/views/AddGM.vue')
  },






  {
    path:'/Test',
    name:'Test',
    component:()=>import('@/views/Test.vue')
  },
  {
    path:'/Test2',
    name:'Test2',
    component:()=>import('@/views/Test2.vue')
  },
  {
    path:'/Test3',
    name:'Test3',
    component:()=>import('@/views/Test3.vue')
  },
  {
    path:'/Test4',
    name:'Test4',
    component:()=>import('@/views/Test4.vue')
  },
]
