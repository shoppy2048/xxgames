// 初始化 Supabase 客户端
const supabaseUrl = 'https://your-supabase-url.supabase.co';
const supabaseKey = 'your-supabase-anon-key';
const supabase = supabase.createClient(supabaseUrl, supabaseKey);

// 设置当前语言
let currentLang = localStorage.getItem('language') || 'en';
document.documentElement.lang = currentLang;

// 获取语言文本
const texts = {
    en: {
        hotGames: 'Hot Games',
        more: 'More',
        search: 'Search'
        // 其他文本...
    },
    zh_CN: {
        hotGames: '热门游戏',
        more: '查看更多',
        search: '搜索'
        // 其他文本...
    }
};

// 应用语言
function applyLanguage() {
    document.getElementById('hotGamesTitle').textContent = texts[currentLang].hotGames;
    // 设置其他文本...
}

// 加载热门游戏
async function loadHotGames() {
    try {
        const { data, error } = await supabase
            .from('games')
            .select('id, name, name_en, description, description_en, logo_url, game_url')
            .order('popularity', { ascending: false })
            .limit(10);
            
        if (error) throw error;
        
        const container = document.getElementById('hotGamesContainer');
        container.innerHTML = '';
        
        data.forEach(game => {
            const gameName = currentLang === 'en' ? game.name_en : game.name;
            const gameDesc = currentLang === 'en' ? game.description_en : game.description;
            
            container.innerHTML += `
                <div class="bg-white rounded-lg shadow-md overflow-hidden hover:shadow-lg transition-shadow">
                    <a href="/game/${game.id}" class="block">
                        <img src="${game.logo_url}" alt="${gameName}" class="w-full h-32 object-cover">
                        <div class="p-4">
                            <h3 class="font-semibold text-gray-800 truncate">${gameName}</h3>
                            <p class="text-sm text-gray-600 mt-1">${gameDesc.substring(0, 60)}${gameDesc.length > 60 ? '...' : ''}</p>
                        </div>
                    </a>
                </div>
            `;
        });
    } catch (error) {
        console.error('Error loading hot games:', error);
    }
}

// 加载分类
async function loadCategories() {
    try {
        const { data, error } = await supabase
            .from('categories')
            .select('id, name, name_en, description, description_en, icon_url, games(id, name, name_en, description, description_en, logo_url, game_url)')
            .order('display_order', { ascending: true });
            
        if (error) throw error;
        
        const container = document.getElementById('categoriesContainer');
        container.innerHTML = '';
        
        data.forEach(category => {
            const categoryName = currentLang === 'en' ? category.name_en : category.name;
            
            let categoryHtml = `
                <section class="mb-12">
                    <div class="flex justify-between items-center mb-6">
                        <h2 class="text-2xl font-bold text-gray-800 border-b pb-2">${categoryName}</h2>
                        <a href="/category/${category.id}" class="text-blue-600 hover:text-blue-800">${texts[currentLang].more}</a>
                    </div>
                    
                    <div class="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-6">
            `;
            
            // 显示最多5个游戏
            const games = category.games.slice(0, 5);
            games.forEach(game => {
                const gameName = currentLang === 'en' ? game.name_en : game.name;
                const gameDesc = currentLang === 'en' ? game.description_en : game.description;
                
                categoryHtml += `
                    <div class="bg-white rounded-lg shadow-md overflow-hidden hover:shadow-lg transition-shadow">
                        <a href="/game/${game.id}" class="block">
                            <img src="${game.logo_url}" alt="${gameName}" class="w-full h-32 object-cover">
                            <div class="p-4">
                                <h3 class="font-semibold text-gray-800 truncate">${gameName}</h3>
                                <p class="text-sm text-gray-600 mt-1">${gameDesc.substring(0, 60)}${gameDesc.length > 60 ? '...' : ''}</p>
                            </div>
                        </a>
                    </div>
                `;
            });
            
            categoryHtml += `
                    </div>
                </section>
            `;
            
            container.innerHTML += categoryHtml;
        });
    } catch (error) {
        console.error('Error loading categories:', error);
    }
}

// 切换语言
function switchLanguage(lang) {
    currentLang = lang;
    localStorage.setItem('language', lang);
    applyLanguage();
    loadHotGames();
    loadCategories();
}

// 初始化
document.addEventListener('DOMContentLoaded', () => {
    applyLanguage();
    loadHotGames();
    loadCategories();
    
    // 语言切换按钮事件监听
    document.querySelectorAll('[data-lang]').forEach(button => {
        button.addEventListener('click', () => {
            switchLanguage(button.dataset.lang);
        });
    });
}); 