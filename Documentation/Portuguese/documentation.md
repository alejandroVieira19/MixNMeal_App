
# Documentação do Código da Aplicação de Receitas

## Introdução
Este documento fornece uma explicação detalhada do código Kotlin usado para gerenciar e exibir categorias de receitas em uma aplicação móvel. O código utiliza Retrofit para requisições de rede, um ViewModel para gerenciamento de estado e Jetpack Compose para a UI.

## Configuração do Retrofit
A biblioteca Retrofit é configurada para fazer requisições HTTP a uma API externa. A URL base para a API está configurada como `https://www.themealdb.com/api/json/v1/1/`, e Gson é usado para converter as respostas JSON em objetos Kotlin.

```kotlin
private val _retrofit = Retrofit.Builder()
    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
```

## Interface de Serviço API
A interface `ApiService` define um método para buscar categorias de receitas. A anotação `@GET` especifica o endpoint `categories.php`. A palavra-chave `suspend` indica que essa função é assíncrona, adequada para uso com corrotinas.

```kotlin
interface ApiService {
    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse
}
```

## ViewModel
`MainViewModel` é responsável por buscar dados e gerenciar o estado da UI. Ele usa LiveData para observar mudanças e atualizar a UI de acordo.

```kotlin
class MainViewModel: ViewModel() {
    private val _categoryState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categoryState

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                _categoryState.value = _categoryState.value.copy(
                    list = response.categories, loading = false, error = null)
            } catch (e: Exception) {
                _categoryState.value = _categoryState.value.copy(
                    loading = false, error = "erro ao buscar categorias ${e.message}")
            }
        }
    }
}
```

## Classes de Dados
As classes `Category` e `CategoriesResponse` são usadas para deserializar os dados JSON da API.

```kotlin
data class Category(val idCategory:String, val strCategory: String,
    val strCategoryThumb: String, val strCategoryDescription:String)

data class CategoriesResponse(val categories: List<Category>)
```

## Componentes UI Composables
Jetpack Compose é usado para criar componentes UI reativos. `RecipeScreen` exibe um indicador de carregamento, uma mensagem de erro, ou uma lista de categorias, baseado no estado atual.

```kotlin
@Composable
fun RecipeScreen(modifier: Modifier = Modifier) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            viewState.loading -> CircularProgressIndicator(modifier.align(Alignment.Center))
            viewState.error != null -> Text("OCORREU UM ERRO")
            else -> CategoryScreen(categories = viewState.list)
        }
    }
}
```

`CategoryScreen` e `CategoryItem` gerenciam o layout das categorias em uma grade e cartões de categoria individuais, respectivamente.

```kotlin
@Composable
fun CategoryScreen(categories: List<Category>) {
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        items(categories) { category -> CategoryItem(category = category) }
    }
}

@Composable
fun CategoryItem(category: Category) {
    Card(modifier = Modifier.padding(8.dp).fillMaxWidth().wrapContentHeight(),
         shape = RoundedCornerShape(16.dp)) {
        Box(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
            Column(modifier = Modifier.padding(8.dp).fillMaxWidth().wrapContentHeight(),
                   horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = rememberAsyncImagePainter(category.strCategoryThumb),
                      contentDescription = null,
                      modifier = Modifier.fillMaxWidth().aspectRatio(1f).clip(RoundedCornerShape(16.dp)))
                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = Color.Gray, thickness = 2.dp, modifier = Modifier.padding(vertical = 8.dp))
                Text(text = category.strCategory, color = Color.Black,
                     style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
                     modifier = Modifier.padding(top = 4.dp))
            }
        }
    }
}
```

Este documento fornece uma visão geral abrangente da estrutura e funcionalidade do código da aplicação.
