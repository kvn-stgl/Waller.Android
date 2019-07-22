package de.kevin_stieglitz.waller.model

data class WallpaperSearchOptions(
    val sorting: DisplayType,
    var searchText: String? = null,
    var categories: String? = null
) {

    fun toQueryMap(): Map<String, String> {
        val map = mapOf(
            "q" to searchText,
            "sorting" to sorting.sorting,
            "categories" to categories
        )

        @Suppress("UNCHECKED_CAST")
        return map.filterValues { it != null } as Map<String, String>
    }
}