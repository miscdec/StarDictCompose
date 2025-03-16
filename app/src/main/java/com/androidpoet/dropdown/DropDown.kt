package com.androidpoet.dropdown

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowLeft
import androidx.compose.material.icons.automirrored.rounded.ArrowRight
import androidx.compose.material.icons.rounded.ArrowLeft
import androidx.compose.material.icons.rounded.ArrowRight
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

val MAX_WIDTH: Dp = 192.dp

/*[animateContent]  enter and exit animation for the menu content. */
@ExperimentalAnimationApi
internal fun animateContent(
    animationProp: AnimationProp,
    enterAnimation: EnterAnimation,
    exitAnimation: ExitAnimation
): ContentTransform {
    return getMetaphorEnterAnimation(animationProp, enterAnimation) togetherWith
            getMetaphorExitAnimation(animationProp, exitAnimation)
}

/*[isNavigatingBack]  check if menu is navigating back. */
fun <T : Any> isNavigatingBack(
    currentMenu: MenuItem<T>,
    nextMenu: MenuItem<T>
): Boolean {
    return currentMenu.hasParent() && nextMenu == currentMenu.parent!!
}

/*[Dropdown]  DropdownMenu wrapper claas to add cascade effect and animations . */

@ExperimentalAnimationApi
@Composable
fun <T : Any> Dropdown(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    menu: MenuItem<T>,
    colors: DropDownMenuColors = dropDownMenuColors(),
    offset: DpOffset = DpOffset.Zero,
    enter: EnterAnimation = EnterAnimation.FadeIn,
    exit: ExitAnimation = ExitAnimation.FadeOut,
    easing: Easing = Easing.FastOutLinearInEasing,
    enterDuration: Int = 500,
    exitDuration: Int = 500,
    onItemSelected: (T) -> Unit,
    onDismissRequest: () -> Unit,
) {

    DropdownMenu(
        modifier = modifier,
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        offset = offset
    ) {

        val state by remember { mutableStateOf(MenuState(menu)) }
        AnimatedContent(
            targetState = state.currentMenuItem,
            transitionSpec = {
                if (isNavigatingBack(initialState, targetState)) {
                    animateContent(
                        AnimationProp(
                            enterDuration,
                            exitDuration,
                            0,
                            easing,
                        ),
                        enter, exit
                    )
                } else {
                    animateContent(
                        AnimationProp(
                            enterDuration,
                            exitDuration,
                            0,
                            easing,
                        ),
                        enter, exit
                    )
                }
            }
        ) { targetMenu ->
            DropdownContent(
                state = state,
                targetMenu = targetMenu,
                onItemSelected = onItemSelected,
                colors = colors,
            )
        }
    }
}

/*[DropdownContent] represents menu content properties  . */
@Composable
fun <T : Any> DropdownContent(
    state: MenuState<T>,
    targetMenu: MenuItem<T>,
    onItemSelected: (T) -> Unit,
    colors: DropDownMenuColors,
) {
    Column(modifier = Modifier) {
        if (targetMenu.hasParent()) {
            CascadeHeaderItem(
                title = targetMenu.title,
                contentColor = colors.contentColor
            ) {
                state.currentMenuItem = targetMenu.parent!!
            }
        }
        if (targetMenu.hasChildren()) {
            for (item in targetMenu.children!!) {
                if (item.hasChildren()) {
                    ParentItem(
                        item.id,
                        item.title,
                        item.icon,
                        colors.contentColor
                    ) { id ->
                        val child = targetMenu.getChild(id)
                        if (child != null) {
                            state.currentMenuItem = child
                        } else {
                            throw IllegalStateException("Invalid item id : $id")
                        }
                    }
                } else {
                    ChildItem(
                        item.id,
                        item.title,
                        item.icon,
                        colors.contentColor,
                        onItemSelected
                    )
                }
            }
        }
    }
}

/*[Space] add space content . */
@Composable
fun Space() {
    Spacer(modifier = Modifier.width(12.dp))
}

/*[MenuItemIcon] icon properties. */
@Composable
fun MenuItemIcon(icon: ImageVector, tint: Color) {
    Icon(
        modifier = Modifier,
        imageVector = icon,
        contentDescription = null,
        tint = tint
    )
}

/*[MenuItemText] text properties. */
@Composable
fun MenuItemText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    isHeaderText: Boolean = false,
) {
    val style = if (isHeaderText) {
        MaterialTheme.typography.titleSmall
    } else {
        MaterialTheme.typography.titleMedium
    }

    Text(
        modifier = modifier,
        text = text,
        style = style,
        color = color,
    )
}

/*[MenuItem] DropdownMenuItem  wrapper to handle onclick and user interaction */
@Composable
fun MenuItem(
    text: @Composable () -> Unit = {},
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = {},
    trailingIcon: @Composable (() -> Unit)? = {},
    enabled: Boolean = true,
) {
    DropdownMenuItem(
        modifier = modifier,
        onClick = onClick,
        interactionSource = remember { MutableInteractionSource() },
        text = text,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        enabled = enabled
    )
}

/*[CascadeHeaderItem] represent header item of the menu */
@Composable
fun CascadeHeaderItem(
    modifier: Modifier = Modifier,
    title: String,
    contentColor: Color,
    onClick: () -> Unit,
) {
    MenuItem(
        text = {
            MenuItemText(
                modifier = Modifier,
                text = title,
                color = contentColor.copy(alpha = MaterialTheme.colorScheme.onSurfaceVariant.alpha),
                isHeaderText = true,
            )
        },
        onClick = { onClick() },
        modifier = modifier,
        leadingIcon = {
            MenuItemIcon(
                icon = Icons.AutoMirrored.Rounded.ArrowLeft,
                tint = contentColor.copy(alpha = MaterialTheme.colorScheme.onSurfaceVariant.alpha)
            )
        },
    )
}


/*[ParentItem] parentItem of the menu*/
@Composable
fun <T> ParentItem(
    id: T,
    title: String,
    icon: ImageVector?,
    contentColor: Color,
    onClick: (T) -> Unit,
) {
    MenuItem(
        text = {
            MenuItemText(
                text = title,
                color = contentColor,
            )
        },
        onClick = { onClick(id) },
        leadingIcon = {
            if (icon != null) {
                MenuItemIcon(icon = icon, tint = contentColor)
            }
        },
        trailingIcon = {
            MenuItemIcon(icon = Icons.AutoMirrored.Rounded.ArrowRight, tint = contentColor)
        },
        enabled = true,
    )
}

/*[ChildItem] childItem of the menu*/
@Composable
fun <T> ChildItem(
    id: T,
    title: String,
    icon: ImageVector?,
    contentColor: Color,
    onClick: (T) -> Unit,
) {
    MenuItem(
        onClick = { onClick(id) },
        leadingIcon = {
            if (icon != null) {
                MenuItemIcon(icon = icon, tint = contentColor)
            }
        },
        text = {
            MenuItemText(
                modifier = Modifier,
                text = title,
                color = contentColor,
            )
        },
        enabled = true,
    )
}
