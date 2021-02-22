import {getRequest} from "./api";

export const initMenu = (router, store) => {
// 先判断 如果已经有菜单是数据狙不要加载了，菜单数据放在routes数组中。
    if (store.state.routes.length > 0) {
        return;
    }
    getRequest("/config/menus").then(data => {
        if (data) {
			
			// 格式化数据
            let fmtRoutes = formatRoutes(data);
            router.addRoutes(fmtRoutes);
			// 加到store中
            store.commit('initRoutes', fmtRoutes);
            store.dispatch('connect');
        }
    })
}
export const formatRoutes = (routes) => {
    let fmRoutes = [];
    routes.forEach(router => {
        // 批量的变量定义，会自赋值给router对应的属性
		let {
            path,
            component,
            name,
            meta,
            iconCls,
            children
        } = router;
		// 判断children是不是数组 是数组说明是一级菜单，递归调用
        if (children && children instanceof Array) {
            children = formatRoutes(children);
        }
        let fmRouter = {
            path: path,
            name: name,
            iconCls: iconCls,
            meta: meta,
            children: children,
			// 动态来加载component值
			// component: () => import ('@/views/own-space/own-space.vue')
            component(resolve) {
                if (component.startsWith("Home")) {
                    require(['../views/' + component + '.vue'], resolve);
                } 
				else if (component.startsWith("Users")) {
                    require(['../views/users/' + component + '.vue'], resolve);
                } else if (component.startsWith("Adv")) {
                    require(['../views/adv/' + component + '.vue'], resolve);
                } else if (component.startsWith("Article")) {
                    require(['../views/article/' + component + '.vue'], resolve);
                } else if (component.startsWith("Topic")) {
                    require(['../views/topic/' + component + '.vue'], resolve);
                } else if (component.startsWith("Sys")) {
                    require(['../views/sys/' + component + '.vue'], resolve);
                }
            }
        }
        fmRoutes.push(fmRouter);
    })
    return fmRoutes;
}