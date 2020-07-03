import Vue from 'vue'
import { Button } from 'vant'
import {Tabbar,TabbarItem} from 'vant'
import {NavBar}  from 'vant'
import { Grid, GridItem } from 'vant';
import { Image } from 'vant';
import { Search } from 'vant';
import { Row, Col } from 'vant';
import { Cell, CellGroup } from 'vant';
import { Collapse, CollapseItem } from 'vant';
import { Divider } from 'vant';
import { Swipe, SwipeItem } from 'vant';
import { Skeleton } from 'vant';
import { Popup } from 'vant';
import { NoticeBar } from 'vant';
import { Tab, Tabs } from 'vant';
import { Field } from 'vant';
import { Dialog } from 'vant';
import { Form } from 'vant';
import { RadioGroup, Radio } from 'vant';
import { DatetimePicker } from 'vant';
import { Pagination } from 'vant';
import { Uploader } from 'vant';
import { Toast } from 'vant';

Vue.use(Button)
Vue.use(Tabbar).use(TabbarItem);
Vue.use(NavBar);
Vue.use(Grid).use(GridItem);
Vue.use(Image);
Vue.use(Search);
Vue.use(Row).use(Col);
Vue.use(Cell).use(CellGroup);
Vue.use(Collapse).use(CollapseItem);
Vue.use(Divider);
Vue.use(Swipe).use(SwipeItem);
Vue.use(Skeleton);
Vue.use(Popup);
Vue.use(NoticeBar);
Vue.use(Tab).use(Tabs);
Vue.use(Field);
Vue.use(Dialog);
Vue.use(Form);
Vue.use(Radio);
Vue.use(RadioGroup);
Vue.use(DatetimePicker);
Vue.use(Pagination);
Vue.use(Uploader);
Vue.use(Toast);